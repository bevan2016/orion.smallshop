package com.orion.smallshop.dto;

import org.springframework.beans.BeanUtils;

import com.orion.smallshop.entity.Item;

public class ItemDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	
	private String name;

	public ItemDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static ItemDto fromEntity(Item entity)
	{
		ItemDto dto = new ItemDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	public static Item toEntity(ItemDto dto)
	{
		Item entity = new Item();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}
}
