package com.ohgiraffers.chap07springjpa.category.service;

import com.ohgiraffers.chap07springjpa.category.model.entity.Category;
import com.ohgiraffers.chap07springjpa.category.repository.CategoryRepository;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    
    private CategoryRepository categoryRepository;
    
    @Autowired // 서비스 클래스가 생성될 때 빈(?)을 주입함
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // 엔티티 자체로 반환하는 일은 거의 없다고 생각해야한다.
    public List<Category> categoryList(){

        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Category categoryFindByName(String name) {

        Category category = categoryRepository.findByCategoryName(name);
        // 전달되는 데이터의 가공 혹은 변환이 필요하다.

        return category;
    }
}
