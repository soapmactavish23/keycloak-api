package com.hkprogrammer.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.api.models.Person;
import com.hkprogrammer.api.services.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PersonResource {

	@Autowired
	private PersonService service;
	
	@GetMapping	
	public List<Person> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/ativos")
	public List<Person> findActive() {
		return service.findActives();
	}

	@PostMapping
	public ResponseEntity<Person> create(@RequestBody @Valid Person obj) {
		Person objSaved = service.create(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(objSaved);
	}
	
	@PutMapping	
	public ResponseEntity<Person> update(@RequestBody @Valid Person obj) {
		Person objSaved = service.update(obj);
		return ResponseEntity.ok(objSaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Integer id) {
		Person objSaved = service.findById(id);
		return ResponseEntity.ok(objSaved);
	}
	
	@PutMapping("/alterar-status/{id}")
	public ResponseEntity<Person> changeStatus(@PathVariable Integer id) {
		Person objSaved = service.changeStatus(id);
		return ResponseEntity.ok(objSaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
}
