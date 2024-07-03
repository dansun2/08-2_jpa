package com.ohgiraffers.section03.bidirection;

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

    @OneToMany(mappedBy = "category") // mappedBy는 주인이 누군지 설정해주는건데 주인이 아닌쪽에 설정해줘야함
    private List<Menu> menuList;

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}
