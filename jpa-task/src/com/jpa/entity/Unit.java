package com.jpa.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Unit
 *
 */
@Entity

public class Unit implements Serializable {

	   
	@Id
	private int id;
	private String unitName;
	private static final long serialVersionUID = 1L;

	public Unit() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
   
}
