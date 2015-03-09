package com.epam.jpa.dao;

import java.util.List;

import com.epam.jpa.domain.Contact;

public interface ContactDAO {

	public void addContact(Contact contact);

	public List<Contact> listContact();

	public void removeContact(Integer id);
}