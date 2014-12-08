package com.jpa.entity;

import javax.persistence.Embeddable;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Embeddable

public class Address {

	private String City;
	private String State;

	public Address() {
		super();
	}   

	public String getCity() {
		return this.City;
	}

	public void setCity(String City) {
		this.City = City;
	}   
	public String getState() {
		return this.State;
	}

	public void setState(String State) {
		this.State = State;
	}
   
}
