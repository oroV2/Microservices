package com.eazybytes.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.eazybytes.account.dto.AccountsContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(info = @Info(title = "Accounts microservice REST API Documentation", description = "Learning Account microservice REST API", version = "v1", contact = @Contact(name = "Onkar Pawar", email = "onkarpawar58@gmail.com", url = "http://orov2.github.io/"), license = @License(name = "Apache 2.0", url = "localhost:8080/")), externalDocs = @ExternalDocumentation(description = "Learning Account microservice REST API", url = "http://localhost:8080/swagger-ui/index.html#/"))
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
