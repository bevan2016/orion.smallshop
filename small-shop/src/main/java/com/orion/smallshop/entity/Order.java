package com.orion.smallshop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.Assert;

import com.orion.smallshop.dto.ItemDto;
import com.orion.smallshop.dto.OrderItemDto;

@Entity
@Table(name="SMALLSHOP_ORDER")
@AttributeOverride(name="id", column=@Column(name="order_id", length=36))
public class Order extends StringIdEntity {
	
	private List<OrderItem> items = new ArrayList<OrderItem>();

	public Order() {		
	}

	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, 
    		mappedBy = "order" )
	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> entities) {
		items = entities;
//		Assert.notNull(entities);
//		this.items.clear();
//		entities.forEach(e->{
//			this.items.add(e);	
//		});
	}

	public void addItem(OrderItem item) {
		if (this.items == null)
			items = new ArrayList<OrderItem>();
		this.items.add(item);
	}
	
	public static OrderItemDto fromEntity(OrderItem entity)
	{
		return new OrderItemDto(ItemDto.fromEntity(entity.getItem()), entity.getCount());
	}

	public static OrderItem toEntity(OrderItemDto dto)
	{
		return new OrderItem(ItemDto.toEntity(dto.getItem()), dto.getCount());		
	}
}
