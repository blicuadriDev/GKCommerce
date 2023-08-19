package com.godknows.gkcommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.godknows.gkcommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT obj"
			+ "FROM Product obj"
			+ "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
	Page<Product> searchByName(String name, Pageable pageable);

}
