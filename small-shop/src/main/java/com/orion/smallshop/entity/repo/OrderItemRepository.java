package com.orion.smallshop.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.orion.smallshop.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, String> {

}
