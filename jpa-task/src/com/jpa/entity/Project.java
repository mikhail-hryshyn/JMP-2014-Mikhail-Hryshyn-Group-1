package com.jpa.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity

public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private int id;
	private String Name;
	
	@ManyToMany
	@JoinTable(
			name = "employee_project",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private Collection<Employee> employees;


	public Collection<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}
	public Project() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
   
}
