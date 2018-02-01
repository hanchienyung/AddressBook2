package com.cy.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressBookRepository extends CrudRepository<AddressBook2, Long> {
   List <AddressBook2> findByLastNameIgnoreCase(String lastName);
}
