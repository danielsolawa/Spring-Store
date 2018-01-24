package com.danielsolawa.storeauth.mappers;


import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.dtos.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto categoryToCategoryDto(Category category);
}
