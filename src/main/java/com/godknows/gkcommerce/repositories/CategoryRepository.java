package com.godknows.gkcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.godknows.gkcommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
