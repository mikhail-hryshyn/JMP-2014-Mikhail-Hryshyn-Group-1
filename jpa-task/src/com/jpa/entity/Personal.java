package com.jpa.entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Personal
 *
 */
@Entity

public class Personal implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private int personal_id;
	private String info;
	
	@OneToOne(mappedBy = "personal")
	private Employee employee;
	

	public Personal() {
		super();
	}   
	public int getId() {
		return this.personal_id;
	}

	public void setId(int id) {
		this.personal_id = id;
	}   
   
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
   
}
