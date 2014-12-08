package com.jpa.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

public enum EmployeeStatus {
FULL_TIME_EMPLOYEE,
PART_TIME_EMPLOYEE
}

/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity

public class Employee implements Serializable {

	   
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;

	public Employee() {
		super();
	}   
	public int getId() {
		return this.id;
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
	
   
}
