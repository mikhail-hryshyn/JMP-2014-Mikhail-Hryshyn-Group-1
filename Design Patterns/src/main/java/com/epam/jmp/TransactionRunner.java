package com.epam.jmp;

import java.util.Arrays;
import java.util.Date;

import com.epam.jmp.model.Status;
import com.epam.jmp.model.film.*;
import com.epam.jmp.transaction.*;
import com.epam.jmp.transaction.film.*;

public class TransactionRunner {

	public static void main(String[] args) {

		String filmName = "Commando";
		Film film = new Film(filmName);

		int ticketsCount = 5;
		double initPrice = 100;
		Date date = new Date();
		Ticket [] tickets = new Ticket[ticketsCount];

		for(int i = 0; i < ticketsCount; i++) {
			tickets[i] = new Ticket(film, i + 1, initPrice, date);
		}

		String newFilmName = "Commando 2";
		Transaction transaction1 = new UpdateFilmTransaction(film, newFilmName);
		TransactionManager.perform(transaction1);
		assert(transaction1.getStatus() == Status.SUCCESS);
		assert(film.getName().equals(newFilmName));

		double newPrice = 1000;
		Ticket ticket1 = tickets[0];
		Transaction transaction2 = new UpdateTicketTransaction(ticket1, newPrice);
		TransactionManager.perform(transaction2);
		assert(transaction2.getStatus() == Status.SUCCESS);
		assert(ticket1.getPrice() == newPrice);

		newFilmName = "Commando 3";
		Transaction transaction3 = new UpdateFilmTransaction(film, newFilmName);

		newPrice = 2000;
		Transaction transaction4 = new UpdateTicketTransaction(ticket1, newPrice);

		Transaction transaction5 = new CompositeTransaction(Arrays.asList(transaction3, transaction4));
		TransactionManager.perform(transaction5);
		assert(transaction5.getStatus() == Status.SUCCESS);
		assert(film.getName().equals(newFilmName));
		assert(ticket1.getPrice() == newPrice);

		newFilmName = "Commando 4";
		Transaction transaction6 = new UpdateFilmTransaction(film, newFilmName);
		assert(transaction6.getStatus() == Status.SUCCESS);

		newPrice = 3000;
		Transaction transaction7 = new UpdateTicketTransaction(ticket1, newPrice);
		assert(transaction7.getStatus() == Status.SUCCESS);
		assert(!film.getName().equals(newFilmName));
		assert(!(ticket1.getPrice() == newPrice));
	}


}
