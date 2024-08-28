package com.example.dao;

import com.example.model.Person;
import java.util.List;

public interface PersonDAO {
	
	public void addPerson(Person person);

	public Person getPerson(int id);

	public List<Person> getAllPersons();

	public void updatePerson(Person person);

	public void deletePerson(int id);
}
