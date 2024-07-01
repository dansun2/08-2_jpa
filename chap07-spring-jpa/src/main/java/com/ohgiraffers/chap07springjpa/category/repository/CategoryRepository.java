package com.ohgiraffers.chap07springjpa.category.repository;

import com.ohgiraffers.chap07springjpa.category.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // 리플렉션을 이용하여 CATEGORY 엔티티의 필드를 읽어올 수 있음
    Category findByCategoryName(String name);
}
