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

	   
	@Id
	private int id;
	private int employeeid;
	private String info;
	private static final long serialVersionUID = 1L;

	public Personal() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}   
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
   
}
