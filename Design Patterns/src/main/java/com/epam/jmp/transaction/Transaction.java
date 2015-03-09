package com.epam.jmp.transaction;


import com.epam.jmp.model.Entity;
import com.epam.jmp.model.Status;

public abstract class Transaction {

	private Status status;

	private Entity originalEntity;

	private Entity beforeEntity;
	private Entity workEntity;

	public Transaction() {}

	public Transaction(Entity entity) {
		this.originalEntity = entity;
		this.beforeEntity = entity.copy();
		this.workEntity = entity.copy();
	}

	public void commit() {
		this.originalEntity.populate(this.workEntity);
		System.out.println("Success: " + this.originalEntity + "\n\n");
	}

	public void abort() {
		this.originalEntity.populate(this.beforeEntity);
		System.out.println("Fail: \n Damaged: " + this.workEntity + 
				"\n Original: " + this.originalEntity + "\n\n");
	}

	public void begin() {
		try {
			executeTx();
			setStatus(Status.SUCCESS);
		}
		catch(Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			setStatus(Status.FAIL);
		}
	}

	protected abstract void executeTx();

	public Status getStatus() {
		return this.status;
	}

	protected Entity getWorkEntity() {
		return this.workEntity;
	}

	protected void setStatus(Status status) {
		this.status = status;
	}

	protected boolean isSuccessful() {
		return this.status == Status.SUCCESS;
	}
}
