package com.ohgiraffers.chap07springjpa.category.controller;

import com.ohgiraffers.chap07springjpa.category.model.entity.Category;
import com.ohgiraffers.chap07springjpa.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category") // category 라는 url이 들어오면 이렇게 매핑하겠다.
public class CategoryContorller {

    private CategoryService categoryService;

    @Autowired // CategoryController 컨트롤러가 생성될 때 밑에를 자동으로 주입해줌
    public CategoryContorller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping // search

    // required = false면 파라미터가 필수로 있지 않아도 된다는 뜻. 기본값은 true이다.
    // *? search라는 파라미터를 가져와서 value값에 넣는다
    public ModelAndView categoryPage(ModelAndView mv,@RequestParam(value = "search", required = false)String name){

        if(name != null && !name.equals("")){ // name이 null이 아니고 ""랑 같지 않으면
            Category category = categoryService.categoryFindByName(name); // 카테고리에다 서비스 클래스에 있는 findbyname 메서드 실행 매개변수는 name

            // 모델에 데이터를 추가한다 "category"라는 이름으로.->뷰에서 이 이름을 사용하여 데이터를 참조함
            // 뒤에꺼는 attributeValue는 모델에 추가할 데이터의 값이다. 결국 윗줄 코드에서 뽑아낸 값을 모델에 추가한다는 뜻
            mv.addObject("category", category);
            mv.setViewName("category/search"); // category/search 라는 뷰 네임 반환
        }else{ // name이 null이면서 ""빈칸이면
            List<Category> categoryList = categoryService.categoryList(); // 서비스 클래스에 카테고리 리스트 메서드 실행해서 List에 넣음
            mv.addObject("categorys", categoryList);
            mv.setViewName("category/page"); // category/page 라는 뷰 네임 반환
        }

        return mv;
    }

}
