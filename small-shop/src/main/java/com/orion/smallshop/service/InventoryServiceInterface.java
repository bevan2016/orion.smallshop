package com.orion.smallshop.service;

import java.util.List;

import com.orion.smallshop.dto.InventoryItemDto;
import com.orion.smallshop.errors.InsufficientInventoryException;

public interface InventoryServiceInterface {
	List<InventoryItemDto> getSummary();
	
	List<InventoryItemDto> checkout(List<InventoryItemDto> items) throws InsufficientInventoryException;

	void checkin(List<InventoryItemDto> items);
}
