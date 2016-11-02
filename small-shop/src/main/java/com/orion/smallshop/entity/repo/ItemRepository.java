package com.orion.smallshop.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.orion.smallshop.entity.Item;

public interface ItemRepository  extends CrudRepository<Item, String> {
	Item findByName(String name);
}

