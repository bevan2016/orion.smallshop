package com.orion.smallshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.orion.smallshop.dto.InventoryItemDto;
import com.orion.smallshop.dto.OrderDto;
import com.orion.smallshop.dto.OrderItemDto;
import com.orion.smallshop.entity.Order;
import com.orion.smallshop.entity.OrderItem;
import com.orion.smallshop.entity.repo.OrderItemRepository;
import com.orion.smallshop.entity.repo.OrderReposiotry;
import com.orion.smallshop.errors.InsufficientInventoryException;

@Component
@Transactional(rollbackOn=Exception.class) 
public class OrderService implements OrderServiceInterface {
	@Autowired
	OrderReposiotry orderRepo;
	
	@Autowired
	OrderItemRepository orderItemRepo;
	
	@Autowired
	InventoryService inventoryService;
	
	@Override
	public List<OrderDto> getAll() {
		Iterable<Order> entities = orderRepo.findAll();
		return toDtos(entities);
	}

	@Override
	public OrderDto placeOrder(OrderDto dto) throws InsufficientInventoryException{
		Assert.notNull(dto);
		Assert.notNull(dto.getItems());
		Assert.isTrue(dto.getItems().size() > 0, "There must be at least one item to place an order" );
		
		if ("".equals(dto.getId()))
			dto.setId(null);
		dto.getItems().forEach(item->{
			if ("".equals(item.getId()))
				item.setId(null);
		});		
		//checkout
		List<InventoryItemDto> inventoryDtos = toInventoryItems(dto.getItems());
		inventoryDtos = inventoryService.checkout(inventoryDtos);
		
		//save order
		//dto.setItems(orderDtos);
		Order entity = orderRepo.save(OrderDto.toEntity(dto));

		//save items
		List<OrderItemDto> orderDtos = toOrderItems(inventoryDtos);
		List<OrderItem> orderEntities = new ArrayList<OrderItem>();
		orderDtos.forEach(oDto->{
			OrderItem oi = OrderItemDto.toEntity(oDto);
			oi.setOrder(entity);
			orderEntities.add(oi);
		});
		
		orderItemRepo.save(orderEntities);
		
		
		Order storedEntity = orderRepo.findOne(entity.getId());
		return OrderDto.fromEntity(storedEntity);
	}
	
	private static List<InventoryItemDto> toInventoryItems (List<OrderItemDto> dtos)
	{
		List<InventoryItemDto> inventoryDtos = new ArrayList<InventoryItemDto> (dtos.size());
		for (OrderItemDto dto: dtos)
		{
			if (dto.getCount() <= 0)
				continue;
			inventoryDtos.add(new InventoryItemDto(dto.getItem(), dto.getCount()));
		}
		
		return inventoryDtos;
	}

	private static List<OrderItemDto> toOrderItems (List<InventoryItemDto> dtos)
	{
		List<OrderItemDto> orderDtos = new ArrayList<OrderItemDto> (dtos.size());
		for (InventoryItemDto dto: dtos)
		{
			orderDtos.add(new OrderItemDto(dto.getItem(), dto.getCount()));
		}
		
		return orderDtos;
	}
	
	public static List<OrderDto> toDtos(Iterable<Order> entities)
	{
		List<OrderDto> dtos = new ArrayList<OrderDto>();
		entities.forEach(e->{
			dtos.add(OrderDto.fromEntity(e));
		});
		
		return dtos;
	}
	
	private Iterable<Order> toEntities(List<OrderDto> dtos)
	{
		List<Order> entities = new ArrayList<Order>(dtos.size()); 
		dtos.forEach(dto->{
			entities.add(OrderDto.toEntity(dto));
		});
		
		return entities;
	}

}
