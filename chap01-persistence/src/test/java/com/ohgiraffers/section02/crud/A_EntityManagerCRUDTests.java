package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerCRUDTests {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll // 테스트가 실행되기 전에 한 번
    public static void initFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest"); // jdbc 커넥션이랑 똑같다
    }

    @BeforeEach // 하나의 테스트 단위마다 실행됨
    public void initManager(){
        entityManager = entityManagerFactory.createEntityManager(); // 엔티티매니저가 영속성에 잡혀있다.
    }

    @AfterAll
    public static void closeFactory(){
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager(){
        entityManager.close();
    }

    @Test
    public void 메뉴코드로_메뉴_조회_테스트(){

        //given
        int menuCode = 2;

        //when
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        System.out.println(foundMenu);

        // then
        Assertions.assertNotNull(foundMenu); // assernotnull은 null이 아닌 녀석
        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
    }

    @Test
    public void 새로운_메뉴_추가_테스트(){
        Menu menu = new Menu();
        menu.setMenuName("jpa 테스트 신규 메뉴");
        menu.setMenuPrice(1000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("y");

        EntityTransaction entityTransaction = entityManager.getTransaction(); //트랜젝션은 매니저의 상태 변화를 위해
        entityTransaction.begin(); //트랜젝션을 스타트하겠다.

        try{
            entityManager.persist(menu); //영속성컨테스트에 지금 작성한 메뉴를 집어넣겠다.
            entityTransaction.commit(); //영구반영시킬거냐. =>데이터에 반영시키겠다는 뜻이라 이 때 쿼리가 날아감.
        }catch (Exception e){
            entityTransaction.rollback(); //롤백시킬거냐
            e.printStackTrace();
        }

        Assertions.assertTrue(entityManager.contains(menu));

    }

    @Test
    public void 메뉴_이름_수정_테스트(){
        Menu menu = entityManager.find(Menu.class, 2);
        System.out.println("menu " + menu);

        String menuNameToChange = "갈치스무디";

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            menu.setMenuName(menuNameToChange);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }

        Assertions.assertEquals(menuNameToChange, entityManager.find(Menu.class, 2).getMenuName());
    }

    @Test
    public void 메뉴_삭제하기_테스트(){
        Menu menuToRemove = entityManager.find(Menu.class,1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            entityManager.remove(menuToRemove);
            entityTransaction.commit();
        }catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }

        Menu removeMenu = entityManager.find(Menu.class,1);
        Assertions.assertEquals(null, removeMenu);

    }
}

