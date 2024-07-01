package com.ohgiraffers.chap07springjpa.menu.service;

import com.ohgiraffers.chap07springjpa.menu.dto.MenuDTO;
import com.ohgiraffers.chap07springjpa.menu.entity.Menu;
import com.ohgiraffers.chap07springjpa.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


    public List<Menu> findAll() {
        List<Menu> menus = menuRepository.findAll();
        return menus;
    }

    public int insertMenu(MenuDTO menu) {

        Menu entityMenu = new Menu().name(menu.getMenuName())
                .categoryCode(menu.getCategoryCode())
                .price(menu.getMenuPrice())
                .orderableStatus(menu.getOrderableStatus())
                .builder();

        Menu newMenu = menuRepository.save(entityMenu);
        int result = 0;
        if(newMenu != null){
            result = 1;
        }
        return result;
    }
}
