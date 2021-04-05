package com.mongodb.workshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.workshop.domain.Post;
import com.mongodb.workshop.repository.PostRepository;
import com.mongodb.workshop.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository userRepository;
	
	
	
	public Post findById(String id) {
		Optional<Post> objeto = userRepository.findById(id);
		return objeto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
