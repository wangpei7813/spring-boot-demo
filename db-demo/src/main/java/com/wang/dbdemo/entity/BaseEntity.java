package com.wang.dbdemo.entity;

import javax.persistence.Id;

public class BaseEntity {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
