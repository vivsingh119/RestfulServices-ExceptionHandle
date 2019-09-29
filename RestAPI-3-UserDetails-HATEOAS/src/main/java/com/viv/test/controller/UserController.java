package com.viv.test.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
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
	public Resource<UserDetails> retriveById(@PathVariable int id) {
		UserDetails user = service.findById(id);
		if (user == null)
			throw new UserNotFoundException("Id:: " + id);
		Resource<UserDetails> resource = new Resource<UserDetails>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).fetchAll());

		// adding link to resource
		resource.add(linkTo.withRel("All users"));

		return resource;
	}

	/*@PostMapping("/users/save")
	public void  saveUser(@RequestBody UserDetails user){
		  UserDetails user1=service.save(user);
		  
	}*/

	@PostMapping("/users/save")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody UserDetails user) {
		UserDetails user1 = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(user1.getId())
				.toUri();
   
		return ResponseEntity.created(location).build();
	}

}
