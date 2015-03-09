package com.epam.jmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.epam.jmp.model.film.Film;
import com.epam.jmp.model.film.Ticket;
import com.epam.jmp.msg.MessageRunnable;
import com.epam.jmp.msg.ChannelRunnable;
import com.epam.jmp.msg.model.TicketMsg;

public class MsgRunner {

	public static void main(String[] args) {
		BlockingQueue<TicketMsg> sharedQueue = new LinkedBlockingQueue<TicketMsg>();
		List<Ticket> tickets = createTickets();

		Thread prodThread = new Thread(new ChannelRunnable(tickets, sharedQueue));
		Thread consThread = new Thread(new MessageRunnable(tickets, sharedQueue));

		prodThread.start();
		consThread.start();
	}

	private static List<Ticket> createTickets() {
		String filmName = "Commando";

		int ticketsCount = 5;
		double initPrice = 100;
		Date date = new Date();
		List<Ticket> tickets = new ArrayList<Ticket>();

		for(int i = 0; i < ticketsCount; i++) {
			Film film = new Film(filmName + i);
			tickets.add(new Ticket(film, i + 1, initPrice + i * 10, date));
		}

		return tickets;
	}

}
