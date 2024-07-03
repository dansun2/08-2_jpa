package com.ohgiraffers.section01.manytoone;


import jakarta.persistence.*;
import lombok.Data;

/*
* @JoinColumn은 다대일 연관 관계에서 사용되는 어노테이션이다.
* @JoinColumn에서 사용할 수 있는 속성들은 다음과 같다.
*
* - name : 참조하는 테이블의 컬럼명을 지정한다.
* - referencedColumnName : 참조되는 컬럼명을 지정한다.
* - nullable : 참조하는 테이블의 컬럼에 null 값을 허용할지 여부를 지정한다.
* - insertable : 새로운 엔티티가 저장될 때, 이 참조 컬럼이 sql insert 문에 포함될지 여부를 지정한다.
* - updateble : 엔티티가 업데이트 될 때, 이 참조 컬럼이 SQL update문에 포함될지 여부를 지정한다.
* - table : 참조하는 테이블의 이름을 지정한다.
* - foreignkey : 참조하는 테이블에서 생성될 외래 키에 대한 추가 정보를 지정한다.
*
* @ManyToOne은 다대일 연관관계에서 사용되는 어노테이션이다.
* @ManyToOne에서 사용할 수 있는 속성들은 다음과 같다.
* - cascade : 연관된 엔티티에 대한 영속성 전이를 설정한다.
* - fetch : 연관된 엔티티를 로딩하는 전략을 설정한다.
* - optional : 연관된 엔티티가 필수인지 선택적인지 설정한다.
* */

@Data
@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class MenuAndCategory {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @JoinColumn(name = "category_code")

    // cascade는 영속성을 함께하겠다는 뜻
    // 내가 쓰는쪽이 Many(메뉴테이블 여러곳에서 카테고리를 참조하고 있음)
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;


}
