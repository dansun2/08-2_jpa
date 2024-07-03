package com.ohgiraffers.section02.onetomany;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "category_and_menu")
@Table(name = "tbl_category")
public class CategoryAndMenu {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @JoinColumn(name = "category_code")
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Menu> menuList;
}
