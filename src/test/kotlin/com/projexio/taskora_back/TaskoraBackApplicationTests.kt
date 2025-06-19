package com.projexio.taskora_back

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
class TaskoraBackApplicationTests {

	@Test
	fun contextLoads() {
	}

	companion object {
		@Container
		val postgres = PostgreSQLContainer("postgres:15-alpine").apply {
			withDatabaseName("testdb")
			withUsername("testuser")
			withPassword("testpass")
		}

		@JvmStatic
		@DynamicPropertySource
		fun overrideProps(registry: DynamicPropertyRegistry) {
			registry.add("spring.datasource.url", postgres::getJdbcUrl)
			registry.add("spring.datasource.username", postgres::getUsername)
			registry.add("spring.datasource.password", postgres::getPassword)
		}
	}

}
