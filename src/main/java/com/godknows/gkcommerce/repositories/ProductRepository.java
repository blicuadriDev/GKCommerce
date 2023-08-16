package com.godknows.gkcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.godknows.gkcommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
