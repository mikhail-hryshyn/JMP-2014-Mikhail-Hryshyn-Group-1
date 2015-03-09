package com.epam.jmp.model.film;

import java.io.Serializable;

import com.epam.jmp.model.Entity;

public class Film extends Entity implements Serializable {

	private static final long serialVersionUID = 5409531597065532249L;

	private String name;

	public Film(String name) {
		super();
		validate(name);
		this.name = name;
	}

	private Film(long id, String name) {
		this(name);
		setId(id);
	}

	@Override
	public String toString() {
		return "Film: id: " + getId() + " - \"" + name + "\"";
	}

	@Override
	public Film copy() {
		return new Film(this.getId(), this.name);
	}

	@Override
	public void populate(Entity entity) {
		Film film = (Film) entity;
		setId(film.getId());
		this.name = film.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		validate(name);
		this.name = name;
	}

	public static int MIN_NAME_LENGTH = 1;
	public static int MAX_NAME_LENGTH = 20;

	public void validate (String name) {
		if(name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new RuntimeException("New name length is not correct: " + name.length());
		}
	}
}
