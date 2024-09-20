package com.lukmanhidayah.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lukmanhidayah.catalog.domain.Category;
import com.lukmanhidayah.catalog.dto.ResultPageResponseDto;
import com.lukmanhidayah.catalog.dto.category.CategoryCreateRequestDto;
import com.lukmanhidayah.catalog.dto.category.CategoryListResponseDto;
import com.lukmanhidayah.catalog.repository.CategoryRepository;
import com.lukmanhidayah.catalog.service.CategoryService;
import com.lukmanhidayah.catalog.util.PaginationUtil;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

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

  @Override
  public ResultPageResponseDto<CategoryListResponseDto> findCategoryList(Integer pages, Integer limit, String sortBy,
      String direction, String categoryName) {
    categoryName = StringUtils.isEmpty(categoryName) ? "%" : "%" + categoryName + "%";
    Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
    Pageable pageable = PageRequest.of(pages, limit, sort);
    Page<Category> pageResult = categoryRepository.findByNameLikeIgnoreCase(categoryName, pageable);

    List<CategoryListResponseDto> categoryListResponseDtos = pageResult.stream().map((category) -> {
      CategoryListResponseDto dto = new CategoryListResponseDto();
      dto.setCode(category.getCode());
      dto.setName(category.getName());
      dto.setDescription(category.getDescription());
      return dto;
    }).collect(Collectors.toList());

    return PaginationUtil.createResultPageDto(categoryListResponseDtos, pageResult.getTotalElements(),
        pageResult.getTotalPages());

  }

  @Override
  public List<Category> findCategories(List<String> categoryCodeList) {
    List<Category> categories = categoryRepository.findByCodeIn(categoryCodeList);

    if (categories.isEmpty()) {
      new BadRequestException("Category can't be found");
    }

    return categories;

  }

  @Override
  public List<CategoryListResponseDto> constructDTO(List<Category> categories) {
    return categories.stream().map((category) -> {
      CategoryListResponseDto dto = new CategoryListResponseDto();
      dto.setCode(category.getCode());
      dto.setName(category.getName());
      dto.setDescription(category.getDescription());
      return dto;
    }).collect(Collectors.toList());
  }

}
