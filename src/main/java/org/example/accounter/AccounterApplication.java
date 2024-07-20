package org.example.accounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AccounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccounterApplication.class, args);
	}

}
