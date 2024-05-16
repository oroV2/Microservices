package com.eazybytes.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
//@ComponentScans({ @ComponentScan("com.eazybytes.loans.controller") })
//@EnableJpaRepositories("com.eazybytes.loans.repository")
//@EntityScan("com.eazybytes.loans.model")
//@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
//@OpenAPIDefinition(info = @Info(title = "Loans microservice REST API Documentation", description = "EazyBank Loans microservice REST API Documentation", version = "v1", contact = @Contact(name = "Madan Reddy", email = "tutor@eazybytes.com", url = "https://www.eazybytes.com"), license = @License(name = "Apache 2.0", url = "https://www.eazybytes.com")), externalDocs = @ExternalDocumentation(description = "EazyBank Loans microservice REST API Documentation", url = "https://www.eazybytes.com/swagger-ui.html"))
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
