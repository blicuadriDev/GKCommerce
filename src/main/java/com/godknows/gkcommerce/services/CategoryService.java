package com.godknows.gkcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godknows.gkcommerce.dtos.CategoryDTO;
import com.godknows.gkcommerce.entities.Category;
import com.godknows.gkcommerce.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> result = categoryRepository.findAll();
		return result.stream().map(x -> new CategoryDTO(x)).toList();
	}
	
	

}
