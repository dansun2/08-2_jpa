package com.ohgiraffers.section03.bidirection;

import com.ohgiraffers.section02.onetomany.Menu;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "bidirection_category")
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @OneToMany(mappedBy = "category")
    private List<Menu> menuList;

}
