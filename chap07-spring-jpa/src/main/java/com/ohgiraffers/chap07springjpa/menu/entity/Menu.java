package com.ohgiraffers.chap07springjpa.menu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_MENU")
public class Menu {

    @Id
    @Column(name = "menu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private Integer menuPrice;

    @Column(name = "category_code")
    private Integer categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    public Menu() {
    }

    private Menu(Integer menuCode, String menuName, Integer menuPrice, Integer categoryCode, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public Integer getMenuCode() {
        return menuCode;
    }
    public String getMenuName() {
        return menuName;
    }
    public Integer getMenuPrice() {
        return menuPrice;
    }
    public Integer getCategoryCode() {
        return categoryCode;
    }
    public String getOrderableStatus() {
        return orderableStatus;
    }


    public Menu name(String name){
        this.menuName = name;
        return this;
    }

    public Menu price(Integer menuPrice) {
        this.menuPrice = menuPrice;
        return this;
    }
    public Menu categoryCode(Integer categoryCode){
        this.categoryCode = categoryCode;
        return this;
    }

    public Menu orderableStatus(String orderableStatus){
        this.orderableStatus = orderableStatus;
        return this;
    }


    public Menu builder(){
        return new Menu(null, menuName, menuPrice, categoryCode, orderableStatus);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}

