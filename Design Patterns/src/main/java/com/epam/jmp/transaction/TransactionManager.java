package com.epam.jmp.transaction;

import com.epam.jmp.model.Status;

public class TransactionManager {

	public static void perform(Transaction tx) {
		tx.begin();
		if(tx.getStatus() == Status.SUCCESS) {
			tx.commit();
		} else {
			tx.abort();
		}
	}
}
