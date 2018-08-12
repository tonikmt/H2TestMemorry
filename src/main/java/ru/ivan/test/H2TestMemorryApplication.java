package ru.ivan.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ru.ivan.test.servise.RepositoryUral;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= {RepositoryUral.class}) 
public class H2TestMemorryApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2TestMemorryApplication.class, args);
	}
}
