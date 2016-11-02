package com.orion.smallshop.util;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

public class ResourceCollection<T> extends Resources<T>implements Serializable {
	private static final long serialVersionUID = 1L;

    private String name = "items";

    private Long start, count;

    private Integer limit;

    public ResourceCollection(List<T> items) {
		this(items, null, 0L, items.size(), new Long(items.size()));
	}

	public ResourceCollection(List<T> items, List<Link> links, Long start, Integer limit, Long count) {
		super(items);
		if (links != null)
			add(links);	
		this.setCount(count);
		this.setLimit(limit);
		this.setStart(start);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
