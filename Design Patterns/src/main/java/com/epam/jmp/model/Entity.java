package com.epam.jmp.model;

import java.util.Date;

public abstract class Entity {

	public Entity() {
		setId(new Date().getTime());
	}
	
	private long id;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public abstract Entity copy();

	public abstract void populate(Entity entity);
}
