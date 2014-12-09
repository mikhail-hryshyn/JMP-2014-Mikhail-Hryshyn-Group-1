package com.jpa.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String firstName;
	private String lastName;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;
	
	@OneToOne
	@JoinColumn(name = "personal_id")
	private Personal personal;
	
	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;
	
	@ManyToMany
	@JoinTable(
			name = "employee_project",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id"))
	private Collection<Project> projects;

	public Employee() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public EmployeeStatus getStatus() {
		return status;
	}
	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}
	public void setId(int id) {
		this.id = id;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Personal getPersonal() {
		return personal;
	}
	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public Collection<Project> getProjects() {
		return projects;
	}
	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}
}
