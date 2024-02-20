package com.hkprogrammer.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hkprogrammer.api.models.Person;
import com.hkprogrammer.api.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	public Person findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	public List<Person> findAll() {
		return repository.findAll();
	}
	
	public List<Person> findActives() {
		return repository.findByStatus(true);
	}
	
	public Person changeStatus(Integer id) {
		Person obj = findById(id);
		Boolean status = !obj.getStatus();
		obj.setStatus(status);
		return repository.save(obj);
	}
	
	public Person create(Person obj) {
		return repository.save(obj);
	}
	
	public Person update(Person obj) {
		Person objSaved = findById(obj.getId());
		
		BeanUtils.copyProperties(obj, objSaved, "id");
		
		return repository.save(objSaved);
	}
	
	public void delete(Integer id) {
		Person obj = findById(id);
		repository.deleteById(obj.getId());
	}
	
}
