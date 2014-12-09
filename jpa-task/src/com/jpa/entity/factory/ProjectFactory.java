package com.jpa.entity.factory;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;

import com.jpa.entity.Employee;
import com.jpa.entity.Project;

public class ProjectFactory {

	EntityManager em;
	
	public ProjectFactory(EntityManager em)
	{
		this.em = em;
	}
	
	public int create(Project p)
	{
		em.persist(p);
		em.flush();
		return p.getId();
	}
	
	public void update(Project project)
	{
		Project p = em.find(Project.class, project.getId());
		p.setName(project.getName());
		p.setEmployees(project.getEmployees());
		
		em.merge(p);
		em.flush();
	}
	
	public void delete(int id)
	{
		em.remove(getById(id));
		em.flush();
	}
	
	public Project getById(int id)
	{
		return em.find(Project.class, id);
	}
	
	public void addEmployee(Project project, Employee employee)
	{
		Collection<Employee> employees = project.getEmployees();
		
		if (employees == null)
		{
			employees = new ArrayList<Employee>();
		}
		employees.add(employee);
		
		project.setEmployees(employees);
		update(project);
	}
}
