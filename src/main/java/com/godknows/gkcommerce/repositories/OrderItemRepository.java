package com.godknows.gkcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.godknows.gkcommerce.entities.OrderItem;
import com.godknows.gkcommerce.entities.OrderItemPK;


public interface OrderItemRepository extends JpaRepository <OrderItem, OrderItemPK>{

}
