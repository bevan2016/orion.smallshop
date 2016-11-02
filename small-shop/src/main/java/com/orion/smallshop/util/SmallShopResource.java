package com.orion.smallshop.util;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class SmallShopResource<T> extends Resource<T> {

	public SmallShopResource(T content) {
		super(content);
	}

	public SmallShopResource(T content, Link... links) {
		super(content, links);
	}

	public void addLink(Link link)
	{
		if (link != null)
			add(link);
	}
}
