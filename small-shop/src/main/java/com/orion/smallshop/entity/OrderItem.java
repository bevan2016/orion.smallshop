package com.orion.smallshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SMALLSHOP_ORDERITEM")
public class OrderItem extends StringIdEntity{
	
	private Order order;
	
	private Item item;
	
	private int count;

	public OrderItem() {

	}

	public OrderItem(Item item, int count) {
		if (item == null || count < 0)
			// no need to localize since the message is only for internal use.
			throw new IllegalArgumentException("Item is null or the count is negative.");
		this.item = item;
		this.count = count;
	}

	@ManyToOne
	@JoinColumn(name="order_id")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne()
	@JoinColumn(name = "item_id", nullable = false)
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Item must not be null.");
		this.item = item;
	}

	@Column(name = "ITEM_CNT")
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		if (count < 0)
			throw new IllegalArgumentException("The count must not less than zero.");
		this.count = count;
	}
}
