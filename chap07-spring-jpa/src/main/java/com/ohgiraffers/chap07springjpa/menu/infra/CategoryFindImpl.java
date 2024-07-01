package com.ohgiraffers.chap07springjpa.menu.infra;

import com.ohgiraffers.chap07springjpa.category.model.entity.Category;
import com.ohgiraffers.chap07springjpa.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CategoryFindImpl implements CategoryFind{


    private CategoryService categoryService;

    @Autowired
    public CategoryFindImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<Category> categoryAll() {
        List<Category> categoryList = categoryService.categoryList();

        return categoryList;
    }
}
