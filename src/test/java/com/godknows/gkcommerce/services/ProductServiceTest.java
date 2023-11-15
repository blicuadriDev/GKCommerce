package com.godknows.gkcommerce.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.godknows.gkcommerce.dtos.ProductDTO;
import com.godknows.gkcommerce.entities.Product;
import com.godknows.gkcommerce.factories.ProductFactory;
import com.godknows.gkcommerce.repositories.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repository;
	
	private Long existingId, unexistingId;
	private Product product;
	private String productName;
	
	@BeforeEach
	private void setUp() {
		existingId = 1L;
		unexistingId = 2L;
		productName = "PlyStation 5";
		product = ProductFactory.createProduct(productName);
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
	}
	
	
	@Test
	public void findByIdShouldReturnProductDTOWhenIdExistis() {
		
		ProductDTO result = service.findById(existingId);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), product.getId());
		Assertions.assertEquals(result.getName(), product.getName());
	}

}
