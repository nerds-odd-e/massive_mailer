package com.odde.massivemailer.model;

import static org.junit.Assert.*;

import com.odde.TestWithDB;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(TestWithDB.class)
public class ContactPersonTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testCreateContactObjectWithoutCompany() {
		
		String name = "name";
		String email = "email@abc.com";
		String lastname = "lastname";
		ContactPerson person = new ContactPerson(name, email, lastname);
		
		assertEquals(name, person.getName());
		assertEquals(email, person.getEmail());
		assertEquals(lastname, person.getLastname());
		assertEquals("", person.getCompany());
	}
	
	
	@Test
	public void testCreateContactObjectWithCompany() {
		
		String name = "name";
		String email = "email@abc.com";
		String lastname = "lastname";
		String company = "myCompany";
		ContactPerson person = new ContactPerson(name, email, lastname, company);
		
		assertEquals(name, person.getName());
		assertEquals(email, person.getEmail());
		assertEquals(lastname, person.getLastname());
		assertEquals(company, person.getCompany());
	}


	@Test
	public void testCreateContactObjectWithLocation() {

		String name = "name";
		String email = "email@abc.com";
		String lastname = "lastname";
		String company = "myCompany";
		String location = "Singapore";
		ContactPerson person = new ContactPerson(name, email, lastname, company, location);

		assertEquals(name, person.getName());
		assertEquals(email, person.getEmail());
		assertEquals(lastname, person.getLastname());
		assertEquals(company, person.getCompany());
		assertEquals(location, person.getLocation());
	}

	@Test
	public void testCreateContactObjectValuesWithLocationAndCoOrdinates() {

		String name = "name";
		String email = "email@abc.com";
		String lastname = "lastname";
		String company = "myCompany";
		String location = "Singapore";
		ContactPerson person = new ContactPerson(name, email, lastname, company, location);

		assertEquals(name, person.getName());
		assertEquals(email, person.getEmail());
		assertEquals(lastname, person.getLastname());
		assertEquals(company, person.getCompany());
		assertEquals(location, person.getLocation());
		assertNotNull(person.getLatitude());
		assertNotNull(person.getLongitude());

	}


	@Test
	public void testCreateContactObjectValuesInDBWithLocationAndCoOrdinates() throws  Exception{
		String name = "name";
			String email = "email@abc.com";
			String lastname = "lastname";
			String company = "myCompany";
			String location = "Singapore";
			ContactPerson person = new ContactPerson(name, email, lastname, company, location);
			ContactPerson personInDB =CreateContactInDB(person);
			assertNotNull(personInDB.getLatitude());
			assertNotNull(personInDB.getLongitude());
	}

	private ContactPerson CreateContactInDB(ContactPerson person) throws Exception {

		person.saveIt();
		return person;
	}



	@Test
	public void testGetInvalidAttributeValue() throws Exception {
		ContactPerson contact =  new ContactPerson("John", "john@gmail.com", "Doe");
		thrown.expect(IllegalArgumentException.class);
		contact.getAttribute("Invalid");
	}


}
