package com.orion.smallshop.dto;

import com.orion.smallshop.entity.InventoryItem;

//might be mapped to a table in database
public class InventoryItemDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	
	private ItemDto item;
	private int count;

	public InventoryItemDto(ItemDto item, int count) {
		if (item == null || count < 0)
			//no need to localize since the message is only for internal use.
			throw new IllegalArgumentException("Item is null or the count is negative.");
		this.item = item;
		this.count = count;
	}

	public ItemDto getItem() {
		return item;
	}

	public int getCount() {
		return count;
	}

	public void setItem(ItemDto item) {
		this.item = item;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static InventoryItemDto fromEntity(InventoryItem entity)
	{
		InventoryItemDto dto = new InventoryItemDto(ItemDto.fromEntity(entity.getItem()), entity.getCount());
		dto.setId(entity.getId());
		return dto;
	}
	
	public static InventoryItem toEntity(InventoryItemDto dto)
	{
		InventoryItem entity = new InventoryItem(ItemDto.toEntity(dto.getItem()), dto.getCount());
		entity.setId(dto.getId());
		return entity;
	}

}
