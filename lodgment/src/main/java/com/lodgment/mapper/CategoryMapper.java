package com.lodgment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lodgment.domain.Category;



@Mapper
public interface CategoryMapper {

	List<Category> getAllCategories();
	Category getcateCategoryById(String categoryId);
}
