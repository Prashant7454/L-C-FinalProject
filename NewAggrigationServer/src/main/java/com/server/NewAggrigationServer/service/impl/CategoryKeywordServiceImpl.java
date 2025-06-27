package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.CategoryKeywordDTO;
import com.server.NewAggrigationServer.model.CategoryKeyword;
import com.server.NewAggrigationServer.repository.CategoryKeywordRepository;
import com.server.NewAggrigationServer.service.CategoryKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryKeywordServiceImpl implements CategoryKeywordService {

    @Autowired
    private CategoryKeywordRepository repository;

    @Override
    public CategoryKeywordDTO createCategoryKeyword(CategoryKeywordDTO dto) {
        CategoryKeyword entity = new CategoryKeyword();
        entity.setCategoryId(dto.getCategoryId());
        entity.setKeywordId(dto.getKeywordId());

        CategoryKeyword saved = repository.save(entity);

        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<CategoryKeywordDTO> getAllCategoryKeywords() {
        return repository.findAll().stream().map(entity -> {
            CategoryKeywordDTO dto = new CategoryKeywordDTO();
            dto.setId(entity.getId());
            dto.setCategoryId(entity.getCategoryId());
            dto.setKeywordId(entity.getKeywordId());
            return dto;
        }).collect(Collectors.toList());
    }
}
