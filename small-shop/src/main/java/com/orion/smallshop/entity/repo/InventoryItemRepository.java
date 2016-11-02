package com.orion.smallshop.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.orion.smallshop.entity.InventoryItem;
import com.orion.smallshop.entity.Item;

public interface InventoryItemRepository extends CrudRepository<InventoryItem, String>{
	InventoryItem findByItem(Item item);

}
