package com.epam.jpa.service;

import java.util.List;

import com.epam.jpa.domain.Contact;

public interface ContactService {

	public void addContact(Contact contact);

	public List<Contact> listContact();

	public void removeContact(Integer id);
}
