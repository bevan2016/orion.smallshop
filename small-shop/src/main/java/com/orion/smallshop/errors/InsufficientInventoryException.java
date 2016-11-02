package com.orion.smallshop.errors;

import com.orion.smallshop.dto.InventoryItemDto;

public class InsufficientInventoryException extends SmallShopException {

	private static final long serialVersionUID = 1L;
	private InventoryItemDto item;
	
	public InsufficientInventoryException(InventoryItemDto item) {
		super(ErrorCode.PRODUCT_INSUFFICIENT);
		this.item = item;
	}

	public InventoryItemDto getItem() {
		return item;
	}

}
