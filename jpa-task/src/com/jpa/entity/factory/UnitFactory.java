package com.jpa.entity.factory;

import javax.persistence.EntityManager;

import com.jpa.entity.Unit;

public class UnitFactory {

	EntityManager em;
	
	public UnitFactory(EntityManager em)
	{
		this.em = em;
	}
	
	public int create(Unit u)
	{
		em.persist(u);
		em.flush();
		return u.getId();
	}
	
	public void update(Unit unit)
	{
		Unit u = em.find(Unit.class, unit.getId());
		u.setUnitName(unit.getUnitName());
		u.setEmployees(unit.getEmployees());
		
		em.merge(u);
		em.flush();
	}
	
	public void delete(int id)
	{
		em.remove(getById(id));
		em.flush();
	}
	
	public Unit getById(int id)
	{
		return em.find(Unit.class, id);
	}
	
}
