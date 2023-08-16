package com.godknows.gkcommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godknows.gkcommerce.dtos.ProductDTO;
import com.godknows.gkcommerce.entities.Product;
import com.godknows.gkcommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> result = productRepository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);
		return dto;
	}
	/*OR ELSE
	 * 	@Transactional(readOnly = true)
	 * 	public ProductDTO findById(Long id) {
	 * 		Product product = productRepository.findById(id).get();
	 * 		return new ProductDTO(product);
	 * 	}
	 */

}
