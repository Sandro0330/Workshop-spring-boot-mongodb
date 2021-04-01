package com.mongodb.workshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mongodb.workshop.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//userRepository.deleteAll(); //deleta todos os cadastros do banco
		
//		User regina = new User(null, "Regina Castro", "regina@gmail.com");
//		User maria = new User(null, "Maria Das Dores", "maria@gmail.com");
//		User uilson = new User(null, "Uilson Alves", "alves@gmail.com");
//		
//		userRepository.saveAll(Arrays.asList(regina, maria, uilson));
//		
	}

}
