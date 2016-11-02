package com.orion.smallshop.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.util.Assert;

import com.orion.smallshop.dto.InventoryItemDto;
import com.orion.smallshop.dto.OrderDto;

public class ResourceAssembler {
	
	public static SmallShopResource<InventoryItemDto> assembleInventoryItem(InventoryItemDto item)
	{
		Assert.notNull(item, "Given item must not be null!");
		SmallShopResource<InventoryItemDto> resItem = new SmallShopResource<InventoryItemDto>(item);
		//self
		resItem.add(new Link(RESTConstants.URI_ITEMS+"/"+item.getItem().getId(), Link.REL_SELF));
		//we may add others such as update/delete
		return resItem;
	}

	public static ResourceCollection<SmallShopResource<InventoryItemDto>> assembleInventoryItems(List<InventoryItemDto> items)
	{
		if (items == null)
			items = new ArrayList<InventoryItemDto>();
		List<SmallShopResource<InventoryItemDto>> resList = new ArrayList<SmallShopResource<InventoryItemDto>>();
		items.forEach(item->{
			resList.add(assembleInventoryItem(item));			
		});
		ResourceCollection<SmallShopResource<InventoryItemDto>> rc = new ResourceCollection<SmallShopResource<InventoryItemDto>>(resList);
		StringBuilder uri = new StringBuilder();
		uri.append(RESTConstants.URI_ITEMS).
			append("?").
			append("start=").append(rc.getStart()).
			append("&limit=").append(rc.getLimit());
		
		rc.add(new Link(uri.toString()));
		return rc;
	}

	public static SmallShopResource<OrderDto> assembleOrder(OrderDto order) {
		Assert.notNull(order, "Given order must not be null!");
		Assert.isTrue(order.getItems().size() > 0, "Given order must consist at least one item!");
		SmallShopResource<OrderDto> resOrder = new SmallShopResource<OrderDto>(order);
		//self
		resOrder.add(new Link(RESTConstants.URI_ORDER+"/"+order.getId(), Link.REL_SELF));
		//we may add others such as update/delete
		return resOrder;
	}

	public static ResourceCollection<SmallShopResource<OrderDto>> assembleOrders(List<OrderDto> orders) {
		if (orders == null)
			orders = new ArrayList<OrderDto>();
		
		List<SmallShopResource<OrderDto>> resList = new ArrayList<SmallShopResource<OrderDto>>();
		orders.forEach(order->{
			resList.add(assembleOrder(order));			
		});
		
		ResourceCollection<SmallShopResource<OrderDto>> rc = new ResourceCollection<SmallShopResource<OrderDto>>(resList);
		StringBuilder uri = new StringBuilder();
		uri.append(RESTConstants.URI_ORDER).
			append("?").
			append("start=").append(rc.getStart()).
			append("&limit=").append(rc.getLimit());
		
		rc.add(new Link(uri.toString()));
		return rc;
	}

}
