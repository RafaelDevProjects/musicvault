package com.rafaeltech.musicvault;

import com.rafaeltech.musicvault.main.Main;
import com.rafaeltech.musicvault.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicvaultApplication implements CommandLineRunner {

	@Autowired
	private ArtistRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MusicvaultApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.displayMenu();
	}
}
