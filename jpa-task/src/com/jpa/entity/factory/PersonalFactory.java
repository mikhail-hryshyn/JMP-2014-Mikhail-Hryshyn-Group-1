package com.jpa.entity.factory;

import javax.persistence.EntityManager;

import com.jpa.entity.Personal;

public class PersonalFactory {

	EntityManager em;
	
	public PersonalFactory(EntityManager em)
	{
		this.em = em;
	}
	
	public int create(Personal p)
	{
		em.persist(p);
		em.flush();
		return p.getId();
	}
	
	public void update(Personal pInfo)
	{
		Personal p = em.find(Personal.class, pInfo.getId());
		p.setInfo(pInfo.getInfo());

		em.merge(p);
		em.flush();
	}
	
	public void delete(int id)
	{
		em.remove(getById(id));
		em.flush();
	}
	
	public Personal getById(int id)
	{
		return em.find(Personal.class, id);
	}
}
