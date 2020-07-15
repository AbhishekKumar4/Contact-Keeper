package com.contactkeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class ContactKeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactKeeperApplication.class, args);
	}

}
