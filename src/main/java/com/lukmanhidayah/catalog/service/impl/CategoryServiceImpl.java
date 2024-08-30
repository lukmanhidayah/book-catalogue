package com.lukmanhidayah.catalog.service.impl;

import org.springframework.stereotype.Service;

import com.lukmanhidayah.catalog.domain.Category;
import com.lukmanhidayah.catalog.dto.category.CategoryCreateRequestDto;
import com.lukmanhidayah.catalog.repository.CategoryRepository;
import com.lukmanhidayah.catalog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

  private static CategoryRepository categoryRepository;

  @Override
  public void createAndUpdateCategory(CategoryCreateRequestDto createCategoryDto) {
    Category category = categoryRepository.findByCode(createCategoryDto.getCode()).orElse(new Category());

    if (category.getCode() == null) {
      category.setCode(createCategoryDto.getCode().toLowerCase());
    }

    category.setName(createCategoryDto.getCategoryName());
    category.setDescription(createCategoryDto.getDescription());

    categoryRepository.save(category);
  }

}
