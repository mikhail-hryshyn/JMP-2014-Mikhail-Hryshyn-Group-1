package com.jpa.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Unit
 *
 */
@Entity

public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private int unit_id;
	private String unitName;
	
	@OneToMany(mappedBy = "unit")
	private Collection<Employee> employees;

	public Collection<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}
	public Unit() {
		super();
	}   
	public int getId() {
		return this.unit_id;
	}

	public void setId(int id) {
		this.unit_id = id;
	}   
	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
   
}
