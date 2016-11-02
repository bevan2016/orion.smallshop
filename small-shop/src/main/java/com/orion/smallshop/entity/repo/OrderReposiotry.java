package com.orion.smallshop.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.orion.smallshop.entity.Order;

public interface OrderReposiotry extends CrudRepository<Order, String> {

}
