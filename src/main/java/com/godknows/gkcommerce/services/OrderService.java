package com.godknows.gkcommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godknows.gkcommerce.dtos.OrderDTO;
import com.godknows.gkcommerce.dtos.OrderItemDTO;
import com.godknows.gkcommerce.entities.Order;
import com.godknows.gkcommerce.entities.OrderItem;
import com.godknows.gkcommerce.entities.OrderStatus;
import com.godknows.gkcommerce.entities.Product;
import com.godknows.gkcommerce.entities.User;
import com.godknows.gkcommerce.repositories.OrderItemRepository;
import com.godknows.gkcommerce.repositories.OrderRepository;
import com.godknows.gkcommerce.repositories.ProductRepository;
import com.godknows.gkcommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new OrderDTO(order);
	}

	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		
		Order order = new Order();
		
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		User user = userService.authenticated();
		order.setClient(user);
		
		for(OrderItemDTO itemDto : dto.getItems() ) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			order.getItems().add(item);
		}
		
		orderRepository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}

}
