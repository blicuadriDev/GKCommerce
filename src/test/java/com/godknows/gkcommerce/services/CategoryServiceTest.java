package com.godknows.gkcommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.godknows.gkcommerce.dtos.CategoryDTO;
import com.godknows.gkcommerce.entities.Category;
import com.godknows.gkcommerce.factories.CategoryFactory;
import com.godknows.gkcommerce.repositories.CategoryRepository;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {
	
	@InjectMocks
	private CategoryService service;
	
	@Mock
	private CategoryRepository repository;
	
	Category category;
	List<Category> list;
	
	@BeforeEach
	private void setUp() throws Exception{
		category = CategoryFactory.createCategory();
		list = new ArrayList<>();
		list.add(category);
		
		Mockito.when(repository.findAll()).thenReturn(list);
	}
	
	@Test
	public void findAllShouldReturnListCategoryDTO() {
		List<CategoryDTO> result = service.findAll();
		
		Assertions.assertEquals(result.size(), 1);
		Assertions.assertEquals(result.get(0).getId(), category.getId());
		Assertions.assertEquals(result.get(0).getName(), category.getName());
	}
	

}
