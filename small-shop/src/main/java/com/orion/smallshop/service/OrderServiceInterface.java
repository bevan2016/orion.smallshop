package com.orion.smallshop.service;

import java.util.List;

import com.orion.smallshop.dto.OrderDto;
import com.orion.smallshop.errors.InsufficientInventoryException;

public interface OrderServiceInterface {
	/**
	 * Query all of the orders. 
	 * @return
	 */
	List<OrderDto> getAll();
	
	/**
	 * Place an order.
	 * @param order
	 * @return
	 */
	OrderDto placeOrder(OrderDto order) throws InsufficientInventoryException;
}
