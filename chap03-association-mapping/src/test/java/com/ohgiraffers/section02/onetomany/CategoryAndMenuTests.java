package com.ohgiraffers.section02.onetomany;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class CategoryAndMenuTests {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager(){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager(){
        this.entityManager.close();
    }

    @AfterAll
    public static void closeFactory(){
        entityManagerFactory.close();
    }

    @Test
    public void 일대다_연관관계_객체_그래프_탐색을_이용한_조회_테스트(){
        int categoryCode = 10;
        CategoryAndMenu categoryAndMenu = entityManager.find(CategoryAndMenu.class, categoryCode);
        Assertions.assertNotNull(categoryAndMenu);
        System.out.println(categoryAndMenu);
    }

    @Test
    void 일대다_연관관계_객체_삽입_테스트(){ // test코드는 기본 접근제어자가 public이라 안 써도 됨
        CategoryAndMenu categoryAndMenu = new CategoryAndMenu();
        categoryAndMenu.setCategoryCode(888);
        categoryAndMenu.setCategoryName("일대다 추가 카테고리");
        categoryAndMenu.setRefCategoryCode(null);

        List<Menu> menuList = new ArrayList<>();
        Menu menu = new Menu();
        menu.setMenuCode(777);
        menu.setMenuName("일대다 아이스크림");
        menu.setMenuPrice(80000);
        menu.setOrderableStatus("Y");
        menu.setCategoryCode(categoryAndMenu.getCategoryCode());

        menuList.add(menu);
        categoryAndMenu.setMenuList(menuList);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(categoryAndMenu);
        entityTransaction.commit();

        CategoryAndMenu foundCategoryAndMenu = entityManager.find(CategoryAndMenu.class, 888);
        System.out.println(foundCategoryAndMenu);
    }


}
