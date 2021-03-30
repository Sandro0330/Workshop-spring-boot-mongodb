package com.mongodb.workshop.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.workshop.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		List<User> list = new ArrayList<>();
		
		User maria = new User("1", "Maria Silva", "maria@gmail.com");
		User uilson = new User("2", "Uilson Alves", "uilson@gmail.com");
		
		list.addAll(Arrays.asList(maria, uilson));
		return ResponseEntity.ok().body(list);
	}
}
