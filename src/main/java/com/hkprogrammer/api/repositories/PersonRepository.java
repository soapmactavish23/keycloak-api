package com.hkprogrammer.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hkprogrammer.api.models.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	List<Person> findByStatus(Boolean status);
	
}
