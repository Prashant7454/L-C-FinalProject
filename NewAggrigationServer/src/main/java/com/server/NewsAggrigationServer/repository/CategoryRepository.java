package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);

    List<Category> findByIdIn(List<Integer> ids);

    Category findByName(String name);

    List<Category> findByIsHide(Integer isHide);

    List<Category> findByIdInAndIsHide(List<Integer> ids, Integer isHide);
}
