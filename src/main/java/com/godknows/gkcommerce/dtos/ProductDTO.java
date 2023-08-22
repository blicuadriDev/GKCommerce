package com.godknows.gkcommerce.dtos;

import java.util.ArrayList;
import java.util.List;

import com.godknows.gkcommerce.entities.Category;
import com.godknows.gkcommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	
	private Long id;
	
	@Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
	@NotBlank(message="Campo requirido")
	private String name;
	
	@Size(min = 10, message ="Descrição precisa ter pelo menos 10 caracteres")
	private String description;
	
	@NotNull(message = "Campo obrigatório.")
	@Positive(message="O preço tem de ser um valor positivo")
	private Double price;
	
	private String imgUrl;
	
	@NotEmpty(message = "Deve ter pelo menos 1 categoria")
	private List<CategoryDTO> categories = new ArrayList<>();


	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
		for(Category cat : entity.getCategories()) {
			categories.add(new CategoryDTO(cat));
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}
	
	

}
