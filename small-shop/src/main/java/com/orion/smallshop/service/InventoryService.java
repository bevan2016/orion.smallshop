package com.orion.smallshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orion.smallshop.dto.InventoryItemDto;
import com.orion.smallshop.dto.ItemDto;
import com.orion.smallshop.entity.InventoryItem;
import com.orion.smallshop.entity.Item;
import com.orion.smallshop.entity.repo.InventoryItemRepository;
import com.orion.smallshop.entity.repo.ItemRepository;
import com.orion.smallshop.errors.InsufficientInventoryException;

@Component
@Transactional(rollbackOn=Exception.class) 
public class InventoryService implements InventoryServiceInterface {
	@Autowired
	InventoryItemRepository inventoryRepo;

	@Autowired
	ItemRepository itemRepo;
	
	@Override
	public List<InventoryItemDto> getSummary() {
		Iterable<InventoryItem> items = inventoryRepo.findAll(); 
		return toDtos(items);
	}

	@Override
	public List<InventoryItemDto> checkout(List<InventoryItemDto> dtos) throws InsufficientInventoryException {
		if (dtos == null || dtos.size() == 0)
			return new ArrayList<InventoryItemDto>();
		
		for (InventoryItemDto dto : dtos)
		{
			InventoryItem entity = InventoryItemDto.toEntity(dto);
			Item key = itemRepo.findByName(entity.getItem().getName());
			InventoryItem storedItem = inventoryRepo.findByItem(key);
			if (storedItem == null || storedItem.getCount() < dto.getCount())
				throw new InsufficientInventoryException(dto);
			storedItem.setItem(key);
			storedItem.setCount(storedItem.getCount()-dto.getCount());
			inventoryRepo.save(storedItem);
			
			dto.setItem(ItemDto.fromEntity(key));
		}
		
		return dtos;
	}

	@Override
	public void checkin(List<InventoryItemDto> dtos) {
		dtos.forEach(dto->{
			InventoryItem entity = InventoryItemDto.toEntity(dto);
			Item item = itemRepo.findByName(entity.getItem().getName());
			if (item == null)
				item = itemRepo.save(entity.getItem());
			
			InventoryItem storedItem = inventoryRepo.findByItem(item);
			if (storedItem == null)
				storedItem = inventoryRepo.save(entity);
			else
			{
				storedItem.setCount(storedItem.getCount()+dto.getCount());
				inventoryRepo.save(storedItem);
			}
		});
		
	}

	public static List<InventoryItemDto> toDtos(Iterable<InventoryItem> entities)
	{
		List<InventoryItemDto> dtos = new ArrayList<InventoryItemDto>();
		entities.forEach(e->{
			dtos.add(InventoryItemDto.fromEntity(e));
		});
		
		return dtos;
	}
	
	public static Iterable<InventoryItem> toEntities(List<InventoryItemDto> dtos)
	{
		List<InventoryItem> entities = new ArrayList<InventoryItem>(dtos.size());
		dtos.forEach(dto->{
			entities.add(InventoryItemDto.toEntity(dto));
		});
		
		return entities;
	}
}
