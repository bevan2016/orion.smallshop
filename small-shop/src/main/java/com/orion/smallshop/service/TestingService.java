package com.orion.smallshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.orion.smallshop.dto.InventoryItemDto;
import com.orion.smallshop.dto.ItemDto;
import com.orion.smallshop.dto.OrderDto;
import com.orion.smallshop.dto.OrderItemDto;
import com.orion.smallshop.entity.Order;
import com.orion.smallshop.entity.OrderItem;
import com.orion.smallshop.entity.repo.OrderItemRepository;
import com.orion.smallshop.errors.InsufficientInventoryException;

@Component
public class TestingService {

	@Autowired
    InventoryServiceInterface inventoryService;
	
    @Autowired
    OrderServiceInterface orderService;
    
    @Autowired
    OrderItemRepository orderItemRepo;
    
	@PostConstruct
	private void setup()
	{
		String[] itemNames = {"Item A", "Item B"};
		int[] counts = {20, 10};
		List<InventoryItemDto> dtos = new ArrayList<InventoryItemDto>();
		for (int i=0; i<itemNames.length; i++)
		{
			ItemDto item = new ItemDto();
			item.setName(itemNames[i]);
			InventoryItemDto iiDto = new InventoryItemDto(item, counts[i]);
			dtos.add(iiDto);
		}
		inventoryService.checkin(dtos);
		/*
		OrderDto order = new OrderDto();
		for (int i=0; i<itemNames.length; i++)
		{
			ItemDto item = new ItemDto();
			item.setName(itemNames[i]);
			OrderItemDto oDto = new OrderItemDto(item, i+2);
			order.addItem(oDto);
		}
		
		try {
			orderService.placeOrder(order);
		} catch (InsufficientInventoryException e) {
			e.printStackTrace();
		}
		*/
	}
}
