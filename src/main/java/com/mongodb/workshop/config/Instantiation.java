package com.mongodb.workshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mongodb.workshop.domain.Post;
import com.mongodb.workshop.domain.User;
import com.mongodb.workshop.dto.AuthorDTO;
import com.mongodb.workshop.dto.CommentDTO;
import com.mongodb.workshop.repository.PostRepository;
import com.mongodb.workshop.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll(); //deleta todos os cadastros do banco 
		postRepository.deleteAll();
		
		User regina = new User(null, "Regina Castro", "regina@gmail.com");
		User maria = new User(null, "Maria Das Dores", "maria@gmail.com");
		User uilson = new User(null, "Uilson Alves", "alves@gmail.com");

		userRepository.saveAll(Arrays.asList(regina, maria, uilson));
		
		Post post1 = new Post(null, simpleDateFormat.parse("21/03/2021"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, simpleDateFormat.parse("22/03/2021"), "Bom Dia", "Espero que todos tenham um bom dia!", new AuthorDTO(maria));
	 
		CommentDTO c1 = new CommentDTO("Boa viagem!", simpleDateFormat.parse("22/03/2021"), new AuthorDTO(regina));
		CommentDTO c2 = new CommentDTO("Aproveite!", simpleDateFormat.parse("22/03/2021"), new AuthorDTO(uilson));
		CommentDTO c3 = new CommentDTO("Bom dia!", simpleDateFormat.parse("22/03/2021"), new AuthorDTO(regina));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
