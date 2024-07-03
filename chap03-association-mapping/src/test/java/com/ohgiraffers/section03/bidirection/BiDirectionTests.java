package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import org.junit.jupiter.api.*;

public class BiDirectionTests {

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
    public void 양방향_연관관계_매핑_조회_테스트(){
        int menuCode = 10;
        int categoryCode = 10;

        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        Category foundCategory = entityManager.find(Category.class, categoryCode);

        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
        Assertions.assertEquals(categoryCode, categoryCode);

        System.out.println(foundMenu);
        System.out.println(foundCategory);
        foundCategory.getMenuList().forEach(System.out::println);
    }
}
