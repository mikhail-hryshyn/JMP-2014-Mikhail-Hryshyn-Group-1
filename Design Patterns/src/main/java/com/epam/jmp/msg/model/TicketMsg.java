package com.epam.jmp.msg.model;

public class TicketMsg {

	private Long id;
	private double price;

	public TicketMsg(Long id, double price) {
		super();
		this.id = id;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public String toString() {
		return "[Msg: "+ id +", " + price + "]";
	}

}
