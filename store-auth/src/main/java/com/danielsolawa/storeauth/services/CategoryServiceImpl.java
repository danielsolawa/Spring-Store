package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.dtos.CategoryDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.mappers.CategoryMapper;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getCategoryList() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findOne(categoryId);

        if(category == null){
            throw new ResourceNotFoundException();
        }

        return categoryMapper.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto createNewCategory(CategoryDto categoryDto) {
        return saveCategoryDto(categoryDto);
    }

    @Transactional
    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        CategoryDto foundCategory = getCategoryById(categoryId);

        foundCategory.setName(categoryDto.getName());

        return saveCategoryDto(foundCategory);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.delete(categoryId);
    }


    private CategoryDto saveCategoryDto(CategoryDto categoryDto){
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);

        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }
}
