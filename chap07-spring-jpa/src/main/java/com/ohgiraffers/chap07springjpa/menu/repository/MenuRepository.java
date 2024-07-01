package com.ohgiraffers.chap07springjpa.menu.repository;

import com.ohgiraffers.chap07springjpa.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
