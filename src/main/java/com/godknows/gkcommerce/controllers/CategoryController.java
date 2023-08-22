package com.godknows.gkcommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godknows.gkcommerce.dtos.CategoryDTO;
import com.godknows.gkcommerce.services.CategoryService;

@RestController
@RequestMapping(value ="/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<CategoryDTO> catList = categoryService.findAll();
		return ResponseEntity.ok(catList);
	}
	
}
