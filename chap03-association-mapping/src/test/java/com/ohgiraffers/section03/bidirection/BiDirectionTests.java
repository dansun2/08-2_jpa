package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.*;
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

    @Test
    void 양방향_연관관계_주인_객체를_이용한_삽입_테스트(){
        Menu menu = new Menu();
        menu.setMenuName("연관관계 주인");
        menu.setMenuPrice(1000);
        menu.setOrderableStatus("Y");

        menu.setCategory(entityManager.find(Category.class, 4));

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(menu);
        transaction.commit();

        Menu foundMenu = entityManager.find(Menu.class, menu.getMenuCode());
        Assertions.assertEquals(menu.getMenuCode(), foundMenu.getMenuCode());
        System.out.println(foundMenu);
    }
}
