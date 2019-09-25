package com.viv.test.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.viv.test.domain.UserDetails;
import com.viv.test.exception.UserNotFoundException;
import com.viv.test.service.UserServiceDAO;

@RestController
public class UserController {
	@Autowired
	private UserServiceDAO service;

	@GetMapping("/users")
	public List<UserDetails> fetchAll() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public UserDetails retriveById(@PathVariable int id) {
		UserDetails user = service.findById(id);
		if (user == null)
			throw new UserNotFoundException("Id:: " + id);
		return user;
	}

	/*@PostMapping("/users/save")
	public void  saveUser(@RequestBody UserDetails user){
		  UserDetails user1=service.save(user);
		  
	}*/

	@PostMapping("/users/save")
	public ResponseEntity<Object> saveUser(@RequestBody UserDetails user) {
		UserDetails user1 = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(user1.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
