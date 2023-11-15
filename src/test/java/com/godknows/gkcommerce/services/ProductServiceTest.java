package com.godknows.gkcommerce.services;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.godknows.gkcommerce.dtos.ProductDTO;
import com.godknows.gkcommerce.dtos.ProductMinDTO;
import com.godknows.gkcommerce.entities.Product;
import com.godknows.gkcommerce.factories.ProductFactory;
import com.godknows.gkcommerce.repositories.ProductRepository;
import com.godknows.gkcommerce.services.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repository;
	
	private Long existingId, unexistingId;
	private String productName;
	
	private Product product;
	private PageImpl<Product> page;

	
	@BeforeEach
	private void setUp() {
		existingId = 1L;
		unexistingId = 2L;
		productName = "PlyStation 5";
		product = ProductFactory.createProduct(productName);
		page = new PageImpl<>(List.of(product));
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
		Mockito.when(repository.findById(unexistingId)).thenReturn(Optional.empty());
		
		Mockito.when(repository.searchByName(any(),(Pageable)any())).thenReturn(page);
	}
	
	
	@Test
	public void findByIdShouldReturnProductDTOWhenIdExists() {
		
		ProductDTO result = service.findById(existingId);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), product.getId());
		Assertions.assertEquals(result.getName(), product.getName());
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, ()->{
			service.findById(unexistingId);
		});
	}
	
	@Test
	public void findAllShouldReturnPagedProductMinDTO() {
		Pageable pageable = PageRequest.of(0, 12);
		
		Page<ProductMinDTO> result = service.findAll(productName, pageable);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getSize(), 1);
		Assertions.assertEquals(result.iterator().next().getName(), productName);
	}

}
