package com.godknows.gkcommerce.dtos;

import com.godknows.gkcommerce.entities.OrderItem;

public class OrderItemDTO {
	
	private Long productId;
	private String name;
	private Double price;
	private Integer quantity;
	private String imgUrl;
	
	
	
	public OrderItemDTO(Long productId, String name, Double price, Integer quantity,String imgUrl) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imgUrl = imgUrl;
	}
	
	
	public OrderItemDTO(OrderItem entity) {
		productId = entity.getProduct().getId();
		name = entity.getProduct().getName();
		price = entity.getProduct().getPrice();
		quantity = entity.getQuantity();
		imgUrl = entity.getProduct().getImgUrl();
	}


	public Long getProductId() {
		return productId;
	}


	public String getName() {
		return name;
	}


	public Double getPrice() {
		return price;
	}


	public Integer getQuantity() {
		return quantity;
	}
	
	
	
	public Double getSubtotal() {
		return quantity + price;
	}


	public String getImgUrl() {
		return imgUrl;
	}


}
