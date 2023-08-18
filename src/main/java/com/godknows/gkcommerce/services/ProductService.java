package com.godknows.gkcommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godknows.gkcommerce.dtos.ProductDTO;
import com.godknows.gkcommerce.entities.Product;
import com.godknows.gkcommerce.repositories.ProductRepository;
import com.godknows.gkcommerce.services.exceptions.DatabaseException;
import com.godknows.gkcommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> result = productRepository.findById(id);
		Product product = result.orElseThrow( () -> new ResourceNotFoundException("Recurso não encontrado"));
		ProductDTO dto = new ProductDTO(product);
		return dto;
	}
	/*OR ELSE
	 * 	@Transactional(readOnly = true)
	 * 	public ProductDTO findById(Long id) {
	 * 		Product product = productRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Recurso não encontrado"));
	 * 		return new ProductDTO(product);
	 * 	}
	 */
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = productRepository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
	}
	
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyToDto(dto, entity);
		entity = productRepository.save(entity);
		return new ProductDTO(entity);
	}
	
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = productRepository.getReferenceById(id);
			copyToDto(dto, entity);
			entity = productRepository.save(entity);
			return new ProductDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado.");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete (Long id) {
		if(!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado.");
		}
		try {
			productRepository.deleteById(id);	
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	 }
	
	
	
	private void copyToDto (ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
	}

}
