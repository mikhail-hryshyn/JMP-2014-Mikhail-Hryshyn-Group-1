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
	private int id;
	private String info;
	

	public Personal() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
   
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
   
}
