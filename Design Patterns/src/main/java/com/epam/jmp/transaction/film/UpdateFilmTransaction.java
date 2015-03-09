package com.epam.jmp.transaction.film;

import com.epam.jmp.model.film.Film;
import com.epam.jmp.transaction.Transaction;

public class UpdateFilmTransaction extends Transaction {

	private String newName;

	public UpdateFilmTransaction(Film film, String newName) {
		super(film);
		this.newName = newName;
	}

	@Override
	protected void executeTx() {
		((Film)getWorkEntity()).setName(newName);
	}

}
