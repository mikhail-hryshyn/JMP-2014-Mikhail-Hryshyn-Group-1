package com.jpa.entity.factory;

import javax.persistence.EntityManager;

import com.jpa.entity.Employee;

public class EmployeeFactory {
	
	EntityManager em;
	
	public EmployeeFactory(EntityManager em)
	{
		this.em = em;
	}
	
	public int create(Employee employee)
	{
		em.persist(employee);
		em.flush();
		return employee.getId();
	}
	
	public void update(Employee employee)
	{
		Employee e = em.find(Employee.class, employee.getId());
		e.setFirstName(employee.getFirstName());
		e.setLastName(employee.getLastName());
		e.setAddress(employee.getAddress());
		e.setPersonal(employee.getPersonal());
		e.setProjects(employee.getProjects());
		e.setStatus(employee.getStatus());
		e.setUnit(employee.getUnit());
		
		em.merge(e);
		em.flush();
	}
	
	public void delete(int id)
	{
		em.remove(getById(id));
	}
	
	public Employee getById(int id)
	{
		return em.find(Employee.class, id);
	}

}
