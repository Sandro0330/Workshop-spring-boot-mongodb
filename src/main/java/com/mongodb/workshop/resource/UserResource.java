package com.mongodb.workshop.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mongodb.workshop.domain.User;
import com.mongodb.workshop.dto.UserDTO;
import com.mongodb.workshop.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(usuario -> new UserDTO(usuario)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User objeto = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(objeto));
	}
	
	@PostMapping // ou @RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Void> insert(@RequestBody UserDTO objetoDTO) {
		User objeto = service.fromDTO(objetoDTO);
		objeto = service.insert(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objeto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}") //OU  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)  
	public ResponseEntity<Void> update(@RequestBody UserDTO objetoDTO, @PathVariable String id) {
		User objeto = service.fromDTO(objetoDTO);
		objeto.setId(id);
		objeto = service.update(objeto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")  //OU@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)  
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
