package com.orion.smallshop.dto;

import java.util.ArrayList;
import java.util.List;

import com.orion.smallshop.entity.Order;

public class OrderDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	
	private List<OrderItemDto> items = new ArrayList<OrderItemDto>();

	public OrderDto() {
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items.clear();
		this.items.addAll(items);
	}

	public void addItem(OrderItemDto item) {
		this.items.add(item);
	}
	
	public static OrderDto fromEntity(Order entity)
	{
		OrderDto dto = new OrderDto();
		dto.setId(entity.getId());
		entity.getItems().forEach(itemEntity->{
			dto.addItem(OrderItemDto.fromEntity(itemEntity));
		});
		
		return dto;
	}

	public static Order toEntity(OrderDto dto)
	{
		Order entity = new Order();
		entity.setId(dto.getId());
		dto.getItems().forEach(itemDto->{
			entity.addItem(OrderItemDto.toEntity(itemDto));
		});
		
		return entity;		
	}
}
