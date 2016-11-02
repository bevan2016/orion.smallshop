package com.orion.smallshop.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SMALLSHOP_ITEM")
@AttributeOverride(name="id", column=@Column(name="item_id", length=36))
public class Item extends StringIdEntity{
	
	private String name;
	
	@Column(name = "ITEM_NM", unique=true, length = 256)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
