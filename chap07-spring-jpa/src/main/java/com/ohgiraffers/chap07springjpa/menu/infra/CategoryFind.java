package com.ohgiraffers.chap07springjpa.menu.infra;

import com.ohgiraffers.chap07springjpa.category.model.entity.Category;

import java.util.List;

public interface CategoryFind {

    List<Category> categoryAll();
}
