package com.ohgiraffers.mapping.section03.primarykey.table;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Date;

public class SequenceTests {

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


    /* Primary key에는 @Id 어노테이션과 @GeneratedValue 어노테이션을 사용한다.
       @Id 어노테이션은 엔티티 클래스에서 primary key 역할을 하는 필드를 지정할 때 사용된다.
       @GeneratedValue 어노테이션을 함께 사용하면 primary key 값을 자동으로 생성할 수 있다.

      데이터베이스마다 기본 키를 생성하는 방식이 서로 다르다.
      @GeneratedValue 어노테이션은 다음과 같은 속성을 가지고 있다.

   - strategy : 자동 생성 전략을 지정
       - GenerationType.IDENTITY : 기본 키 생성을 데이터베이스에 위임(MySQL의 AUTO_INCREMENT)
       - GenerationType.SEQUENCE : 데이터베이스 시퀀스 객체 사용(ORACLE의 SEQUENCE)
       - GenerationType.TABLE : 키 생성 테이블 사용 -> DB에 종속적이지 않지만 불필요한게 많아서 잘 안 씀.
       - GenerationType.AUTO : 자동 선택 (MySQL이라면 IDENTITY, ORACLE이라면 SEQUENCE로 선택)
   - generator : strategy 값을 GenerationType.TABLE로 지정한 경우 사용되는 테이블 이름을 지정
   - initialValue : strategy 값을 GenerationType.SEQUENCE로 지정한 경우 시퀀스 초기값을 지정
   - allocationSize : strategy 값을 GenerationType.SEQUENCE로 지정한 경우 시퀀스 증가치를 지정
    * */

    @Test
    public void 컬럼에서_사용하는_속성_테스트(){
        // given
        Member member = new Member();
        member.setMemberId("테스트 길동");
        member.setMemberPwd("pass01");
        member.setNickName("user01");
        member.setAddress("강남구1");
        member.setEnrollDate(new Date());
        member.setPhone("");
        member.setMemberRole("게스트");
        member.setStatus("y");

        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(member); // 영속성 컨테스트에 집어 넣은 얘를
        transaction.commit(); // 커밋해서 영구반영 시킴

        // then
        Member foundMember = entityManager.find(Member.class, member.getMemberNo());
        Assertions.assertEquals(member.getMemberNo(), foundMember.getMemberNo());
    }
}
