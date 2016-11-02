package com.orion.smallshop.dto;

import com.orion.smallshop.entity.OrderItem;

public class OrderItemDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private ItemDto item;
	private int count;

	public OrderItemDto() {

	}

	public OrderItemDto(ItemDto item, int count) {
		if (item == null || count < 0)
			// no need to localize since the message is only for internal use.
			throw new IllegalArgumentException("Item is null or the count is negative.");
		this.item = item;
		this.count = count;
	}

	public ItemDto getItem() {
		return item;
	}

	public void setItem(ItemDto item) {
		if (item == null)
			throw new IllegalArgumentException("Item must not be null.");
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		if (count < 0)
			throw new IllegalArgumentException("The count must not less than zero.");
		this.count = count;
	}

	public static OrderItemDto fromEntity(OrderItem entity)
	{
		OrderItemDto dto = new OrderItemDto(ItemDto.fromEntity(entity.getItem()), entity.getCount());
		dto.setId(entity.getId());
		return dto;
	}

	public static OrderItem toEntity(OrderItemDto dto)
	{
		OrderItem entity = new OrderItem(ItemDto.toEntity(dto.getItem()), dto.getCount());
		entity.setId(dto.getId());
		return entity;
	}
}
