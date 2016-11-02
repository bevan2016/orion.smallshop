package com.orion.smallshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="SMALLSHOP_INVENTORY_ITEM")
public class InventoryItem extends StringIdEntity{
	
	private Item item;
	
	private int count;

	public InventoryItem()
	{
		
	}
	
	public InventoryItem(Item item, int count) {
		if (item == null || count < 0)
			//no need to localize since the message is only for internal use.
			throw new IllegalArgumentException("Item is null or the count is negative.");
		this.item = item;
		this.count = count;
	}

    @OneToOne()
    @JoinColumn(name = "item_id", nullable = false)
	public Item getItem() {
		return item;
	}

	@Column(name = "ITEM_CNT")
	public int getCount() {
		return count;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
