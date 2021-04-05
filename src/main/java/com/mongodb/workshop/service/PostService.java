package com.mongodb.workshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.workshop.domain.Post;
import com.mongodb.workshop.repository.PostRepository;
import com.mongodb.workshop.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> objeto = postRepository.findById(id);
		return objeto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return postRepository.searchTitle(text);
	}
}
 