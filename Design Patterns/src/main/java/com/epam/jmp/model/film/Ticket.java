package com.epam.jmp.model.film;

import java.io.Serializable;
import java.util.Date;

import com.epam.jmp.model.Entity;

public class Ticket extends Entity implements Serializable {

	private static final long serialVersionUID = 8495690590560025920L;

	private Film film;
	private int place;
	private double price;
	private Date date;

	public Ticket(Film film, int place, double price, Date date) {
		super();
		this.film = film;
		this.place = place;
		this.price = price;
		this.date = date;
	}

	private Ticket(long id, Film film, int place, double price, Date date) {
		this(film, place, price, date);
		this.setId(id);
	}

	@Override
	public String toString() {
		return "Ticket: id: " + getId() + "\n Film name: " + film.getName() + "\n Place: " + place + 
				"\n Date: " + date + "\n Price: " + price;
	}

	@Override
	public Ticket copy() {
		return new Ticket(this.getId(), this.film, this.place, this.price, this.date);
	}

	@Override
	public void populate(Entity entity) {
		Ticket ticket = (Ticket) entity;
		setId(ticket.getId());
		this.film = ticket.getFilm();
		this.place = ticket.getPlace();
		this.price = ticket.getPrice();
		this.date = ticket.getDate();
	}

	public Film getFilm() {
		return film;
	}

	public int getPlace() {
		return place;
	}

	public double getPrice() {
		return price;
	}

	public Date getDate() {
		return date;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
