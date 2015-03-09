package com.epam.jmp.msg;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.epam.jmp.model.film.Ticket;
import com.epam.jmp.msg.model.TicketMsg;

public class ChannelRunnable implements Runnable {

	private final BlockingQueue<TicketMsg> sharedQueue;
	private final List<Ticket> tickets;

	public ChannelRunnable(List<Ticket> tickets, BlockingQueue<TicketMsg> sharedQueue) {
		this.tickets = tickets;
		this.sharedQueue = sharedQueue;
	}

	//@Override
	public void run() {

		int i = 0;
		try {
			for(Ticket ticket : tickets) {
				TicketMsg msg = new TicketMsg(ticket.getId(), ticket.getPrice() + 10);
				sharedQueue.put(msg);
				System.out.println("Produced [" + i++ + "]: " + msg);
			}
		} catch (InterruptedException ex) {
			System.out.println(ex);
		}
	}

}