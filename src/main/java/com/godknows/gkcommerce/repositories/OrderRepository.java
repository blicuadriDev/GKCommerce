package com.godknows.gkcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.godknows.gkcommerce.entities.Order;


public interface OrderRepository extends JpaRepository <Order, Long>{

}
