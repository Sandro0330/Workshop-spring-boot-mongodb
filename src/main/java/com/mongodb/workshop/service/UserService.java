package com.mongodb.workshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.workshop.domain.User;
import com.mongodb.workshop.dto.UserDTO;
import com.mongodb.workshop.repository.UserRepository;
import com.mongodb.workshop.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> objeto = userRepository.findById(id);
		return objeto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User objeto) {
		return userRepository.insert(objeto);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User fromDTO(UserDTO objetoDTO) {
		return new User(objetoDTO.getId(), objetoDTO.getName(), objetoDTO.getEmail());
	}
}
