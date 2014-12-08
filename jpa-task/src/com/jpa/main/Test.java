package com.jpa.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpa.entity.Address;
import com.jpa.entity.Employee;
import com.jpa.entity.EmployeeStatus;
import com.jpa.entity.Personal;
import com.jpa.entity.Project;
import com.jpa.entity.Unit;
import com.jpa.entity.factory.EmployeeFactory;
import com.jpa.entity.factory.PersonalFactory;
import com.jpa.entity.factory.ProjectFactory;
import com.jpa.entity.factory.UnitFactory;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-task");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Employee e1 = new Employee();
		e1.setFirstName("firstName");
		e1.setLastName("lastName");
		e1.setStatus(EmployeeStatus.FULL_TIME_EMPLOYEE);
		
		Address a = new Address();
		a.setCity("City");
		a.setState("State");
		e1.setAddress(a);
		
		Personal pInf = new Personal();
		pInf.setInfo("pInf");
		
		PersonalFactory pInfF = new PersonalFactory(em);
		pInfF.create(pInf);
		
		e1.setPersonal(pInf);
		
		EmployeeFactory ef = new EmployeeFactory(em);
		
		ef.create(e1);
		
		Employee e2 = new Employee();
		e2.setFirstName("firstName1");
		e2.setLastName("lastName1");
		
		e2.setStatus(EmployeeStatus.PART_TIME_EMPLOYEE);
		
		a = new Address();
		a.setCity("City1");
		a.setState("State1");
		e2.setAddress(a);
		
		pInf = new Personal();
		pInf.setInfo("pInf1");
		pInfF.create(pInf);
		
		e2.setPersonal(pInf);
		
		ef.create(e2);
		
		/////
		Unit u1 = new Unit();
		u1.setUnitName("unit1");
		
		UnitFactory uf = new UnitFactory(em);
		uf.create(u1);
		
		Unit u2 = new Unit();
		u2.setUnitName("unit2");
		uf.create(u2);
		
		Project p = new Project();
		p.setName("project1");
		
		ProjectFactory pf = new ProjectFactory(em);
		pf.create(p);
		
		e1.setUnit(u1);
		ef.update(e1);

		e2.setUnit(u2);
		ef.update(e2);
		
		pf.addEmployee(p, e1);
		pf.addEmployee(p, e2);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}
	

}
