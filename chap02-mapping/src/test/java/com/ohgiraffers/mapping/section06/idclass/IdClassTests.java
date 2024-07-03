package com.ohgiraffers.mapping.section06.idclass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class IdClassTests {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest"); // 앞에 this가 안붙는 이유는 static이 붙어서 참조가 안 됨.
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

    /* 복합키가 존재하는 테이블 매핑의 경우 별도의 방법이 필요하다.
     * 1. @Embeddable : @Embeddable 클래스에 복합 키를 정의하고 엔티티에 @EmbeddedId를 이용해 복합 키 클래스를 매핑한다.
     * 2. @IdClass : 복합키를 필드로 정의한 클래스를 이용해 엔티티 클래스에 복합키에 해당하는 필드에 @Id를 매핑한다.
     *
     * 두 방식 모두 복합키 클래스는 영속성 컨텍스트가 관리하지 않는다는 특징이 있으며, 큰 기능적 차이도 존재하지 않는다.
     * 다만 @Embeddable이 조금 더 객체 지향다운 방법이고, @IdClass는 관계형 데이터 베이스에 가까운 방법이다.
     * */

    @Test
    public void 임베디드_아이디_사용한_복합키_테이블_매핑_테스트(){

        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("1212");
        member.setNickName("홍길동");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityTransaction.commit();

        Member foundMember = entityManager.find(Member.class, new MemberPK(1,"user01"));
        Assertions.assertEquals(member, foundMember);
    }

}

