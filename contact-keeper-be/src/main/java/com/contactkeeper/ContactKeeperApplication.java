package com.contactkeeper;

import com.contactkeeper.security.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties(JwtConfig.class)
public class ContactKeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactKeeperApplication.class, args);
	}

}
