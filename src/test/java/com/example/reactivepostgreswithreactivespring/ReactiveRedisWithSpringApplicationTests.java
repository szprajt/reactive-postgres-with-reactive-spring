package com.example.reactivepostgreswithreactivespring;

import com.example.reactivepostgreswithreactivespring.model.OperatingSystem;
import com.example.reactivepostgreswithreactivespring.model.Phone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.function.Supplier;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReactiveRedisWithSpringApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@LocalServerPort
	private Integer port;

	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			"postgres:15-alpine"
	).withInitScript("init.sql");

	@BeforeAll
	static void beforeAll() {
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		Supplier<Object> rdbcUrl = () -> postgres.getJdbcUrl().replace("jdbc", "r2dbc");
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
		registry.add("spring.r2dbc.url", rdbcUrl);
		registry.add("spring.r2dbc.username", postgres::getUsername);
		registry.add("spring.r2dbc.password", postgres::getPassword);
	}

	@Test
	void testGetAllPhones() {
		webTestClient.get().uri("api/v1/phones")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Phone.class);
	}

	@Test
	void testGetSinglePhone() {
		webTestClient.get().uri("api/v1/phones/1")
				.exchange()
				.expectStatus().isOk()
				.expectBody(Phone.class)
				.isEqualTo(new Phone(1L, "Samsung", "Galaxy S21", OperatingSystem.ANDROID, BigDecimal.valueOf(999.99)));
	}

	@Test
	void testDeletedPhoneCannotBeFound() {
		webTestClient.delete().uri("api/v1/phones/2")
				.exchange()
				.expectStatus()
				.isOk();

		webTestClient.get().uri("api/v1/phones/2")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	void testUpdatePhone() {
		Phone phone = new Phone(3L, "Google", "Pixel 6", OperatingSystem.ANDROID, BigDecimal.valueOf(799.99));
		webTestClient.put().uri("api/v1/phones/3")
				.bodyValue(phone)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Phone.class)
				.isEqualTo(phone);
	}

}
