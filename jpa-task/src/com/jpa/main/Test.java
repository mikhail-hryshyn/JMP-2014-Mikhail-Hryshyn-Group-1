package com.jpa.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpa.entity.Address;
import com.jpa.entity.Employee;
import com.jpa.entity.EmployeeStatus;
import com.jpa.entity.factory.EmployeeFactory;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-task");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Employee e = new Employee();
		e.setFirstName("firstName");
		e.setLastName("lastName");
		e.setStatus(EmployeeStatus.FULL_TIME_EMPLOYEE);
		
		Address a = new Address();
		a.setCity("City");
		a.setState("State");
		e.setAddress(a);
		
		EmployeeFactory ef = new EmployeeFactory(em);
		
		ef.create(e);
		
		e = new Employee();
		e.setFirstName("firstName1");
		e.setLastName("lastName1");
		
		e.setStatus(EmployeeStatus.PART_TIME_EMPLOYEE);
		
		a = new Address();
		a.setCity("City1");
		a.setState("State1");
		e.setAddress(a);
		
		ef.create(e);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
