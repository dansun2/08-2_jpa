package com.ohgiraffers.section01.manytoone;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;


public class ManyToOneAssociationTests {

    /*
    * Association Mapping은 Entity 클래스 간의 관계를 매핑하는 것을 의미한다.
    * 이를 통해 객체를 이용해 데이터베이스의 테이블 간의 관계를 매핑할 수 있다.
    *
    * 다중성에 의한 분류
    * 연관 관계가 있는 객체 관계에서는 실제로 연관을 가지는 객체의 수에 따라 분류된다.
    * - N:1(many To one) 연관관계
    * - 1:N(one to many) 연관관계
    * - 1:1(one to one) 연관관계
    * - N:N(many to many) 연관관계
    *
    * 방향에 따른 분류
    * 테이블의 연관 관계는 외래키를 이용하여 양방향 연관 관계의 특성을 가진다.
    * 참조에 의한 객체의 연관 관계는 단방향이다.
    * 객체간의 연관 관계를 양방향으로 만들고 싶은 경우 반대 쪽에서도 필드를 추가해서 참조를 보관하면 된다.
    * 하지만 엄밀하게 이는 양방향 관계가 아니라 단방향 관계 2개로 볼 수 있다.
    * - 단방향 연관관계
    * - 양방향 연관관계
    * */

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

    /*
    * many to one은 다수의 엔티티가 하나의 엔티티를 참조하는 상황에서 사용된다.
    * 예를 들어 하나의 카테고리가 여러 개의 메뉴를 가질 수 있는 상황에서 메뉴 엔티티가 카테고리 엔티티를 참조하는 것이다.
    * 이 때 메뉴 엔티티가 many, 카테고리 엔티티가 one이 된다.
    * */
    @Test
    public void 다대일_연관관계_객체_그래프_탐색을_이용한_조회_테스트(){
        int menuCode = 10;

        MenuAndCategory menuAndCategory = entityManager.find(MenuAndCategory.class, menuCode);
//        Category menuCategory = menuAndCategory.getCategory()
//        Assertions.assertNotNull(menuCategory);
//        System.out.println("menuCategory = " + menuCategory);
        System.out.println(menuAndCategory.getCategory());
        Category category = menuAndCategory.getCategory();
        System.out.println(category);
    }

    @Test
    public void 다대일_연관관계_객체지향_쿼리를_사용한_카테고리_이름_조회_테스트(){ // 이 밑에 애들은 영속성 컨텍스트에서 관리됨
        // from의 테이블명은 엔티티명이 되어야 한다.
        String jpql = "SELECT c.categoryName from menu_and_category m join m.category c where m.menuCode=15";

        String category = entityManager.createQuery(jpql, String.class).getSingleResult();

        Assertions.assertNotNull(category);
        System.out.println("category = " + category);
    }

    /*
    * commit()을 할 경우 컨텍스트 내에 저장된 영속성 객체를 insert 하는 쿼리가 동작된다.
    * 단, 카테고리가 존재하는 값이 아니므로 부모 테이블(tbl_category)에 값이 먼저 들어있어야 그 카테고리를 참조해서
    * 자식 테이블(tbl_menu)에 데이터를 넣을 수 있다.
    * 영속성 전이란 특정 엔티티를 영속화할 때 연관된 엔티티도 함께 영속화 한다는 의미이다.
    * cascade=CascadeType.PERSIST를 설정하면 MenuAndCategory 엔티티를 영속화 할 때 category 엔티티도 함께 영속화한다.
    * */
    @Test
    void 다대일_연관관계_객체_삽입_테스트(){ // 영속성 전이
        MenuAndCategory menuAndCategory = new MenuAndCategory();
        menuAndCategory.setMenuCode(999);
        menuAndCategory.setMenuName("멸치빙수");
        menuAndCategory.setMenuPrice(199999);

        Category category = new Category();
        category.setCategoryCode(10000);
        category.setCategoryName("신카");
        category.setRefCategoryCode(null);

        menuAndCategory.setCategory(category);
        menuAndCategory.setOrderableStatus("Y");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(menuAndCategory);
        transaction.commit();

        MenuAndCategory foundMenuAndCategory = entityManager.find(MenuAndCategory.class,999);
        Assertions.assertEquals(menuAndCategory.getMenuCode(), foundMenuAndCategory.getMenuCode());
        Assertions.assertEquals(menuAndCategory.getCategory().getCategoryCode(), foundMenuAndCategory.getCategory().getCategoryCode());
    }
}
