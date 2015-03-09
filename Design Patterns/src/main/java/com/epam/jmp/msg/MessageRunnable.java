package com.epam.jmp.msg;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.epam.jmp.model.Status;
import com.epam.jmp.model.film.Ticket;
import com.epam.jmp.msg.model.TicketMsg;
import com.epam.jmp.transaction.Transaction;
import com.epam.jmp.transaction.TransactionManager;
import com.epam.jmp.transaction.film.UpdateTicketTransaction;

public class MessageRunnable implements Runnable {

	private final BlockingQueue<TicketMsg> sharedQueue;
	private final List<Ticket> tickets;

	public MessageRunnable(List<Ticket> tickets, BlockingQueue<TicketMsg> sharedQueue) {
		this.tickets = tickets;
		this.sharedQueue = sharedQueue;
	}

	// @Override
	public void run() {
		while (true) {
			try {
				TicketMsg msg = sharedQueue.take();
				System.out.println("Consumed: " + msg);
				Ticket ticket = getTicket(msg.getId());
				Transaction ticketTx = new UpdateTicketTransaction(ticket, msg.getPrice());
				TransactionManager.perform(ticketTx);
				assert(ticketTx.getStatus() == Status.SUCCESS);
			} catch (InterruptedException ex) {
				System.out.println(ex);
			}
		}
	}

	private Ticket getTicket(Long id) {
		for(Ticket ticket : tickets) {
			if(ticket.getId() == id) {
				return ticket;
			}
		}
		throw new RuntimeException("Ticket [" + id + "] is not found");
	}

}