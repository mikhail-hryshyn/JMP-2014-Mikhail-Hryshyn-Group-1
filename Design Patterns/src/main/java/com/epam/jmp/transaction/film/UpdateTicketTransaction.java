package com.epam.jmp.transaction.film;

import com.epam.jmp.model.film.Ticket;
import com.epam.jmp.transaction.Transaction;

public class UpdateTicketTransaction extends Transaction {

	private double newPrice;

	public UpdateTicketTransaction(Ticket ticket, double newPrice) {
		super(ticket);
		this.newPrice = newPrice;
	}

	@Override
	protected void executeTx() {
		((Ticket)getWorkEntity()).setPrice(newPrice);
	}
}
