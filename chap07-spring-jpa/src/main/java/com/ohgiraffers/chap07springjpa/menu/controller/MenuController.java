package com.ohgiraffers.chap07springjpa.menu.controller;


import com.ohgiraffers.chap07springjpa.category.model.entity.Category;
import com.ohgiraffers.chap07springjpa.category.service.CategoryService;
import com.ohgiraffers.chap07springjpa.menu.dto.MenuDTO;
import com.ohgiraffers.chap07springjpa.menu.entity.Menu;
import com.ohgiraffers.chap07springjpa.menu.infra.CategoryFind;
import com.ohgiraffers.chap07springjpa.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private MenuService menuService;
    private CategoryFind categoryFind;

    @Autowired
    public MenuController(MenuService menuService,CategoryFind categoryFind) {
        this.menuService = menuService;
        this.categoryFind = categoryFind;
    }

    @GetMapping
    public ModelAndView menuPage(ModelAndView mv){
        //메뉴 리스트 조회하기
        List<Menu> menus = menuService.findAll();

        mv.addObject("menus", menus);
        mv.setViewName("/menu/page");
        return mv;
    }

    @GetMapping("/post")
    public ModelAndView postMenu(ModelAndView mv){
        // 카테고리 추가하기
        List<Category> categoryList = categoryFind.categoryAll();

        mv.addObject("categorys", categoryList);
        mv.setViewName("menu/post");
        return mv;
    }

    @PostMapping("post")
    public ModelAndView addMenu(ModelAndView mv, MenuDTO menu){
        int result = menuService.insertMenu(menu);

        String message = null;
        if(result < 0){
            message = "등록실패";
            mv.setViewName("/menu/post");
        }else{
            message = "등록성공";
            mv.setViewName("/menu/page");
        }
        mv.addObject("message", message);

        return mv;
    }
}
