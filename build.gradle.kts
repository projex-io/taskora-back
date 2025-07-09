plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.projexio"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21) // Utilise Java 21
	}
}

repositories {
	mavenCentral() // Dépôt Maven central
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // Data JPA pour les entités
	implementation("org.springframework.boot:spring-boot-starter-web") // Starter pour les web services REST
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // Gérer JSON avec Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect") // Réflexion pour Kotlin
	implementation("org.postgresql:postgresql:42.7.7") // Driver PostgreSQL
	implementation("org.liquibase:liquibase-core:4.23.2")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

	// Test dependencies
	testImplementation("org.testcontainers:testcontainers:1.19.7") // Testcontainers pour dockerized tests
	testImplementation("org.testcontainers:junit-jupiter:1.19.7") // Intégration Junit Testcontainers
	testImplementation("org.testcontainers:postgresql:1.19.7") // Intégration PostgreSQL avec Testcontainers
	testImplementation("io.mockk:mockk:1.13.9") // Mocking framework pour Kotlin
	testImplementation("org.springframework.boot:spring-boot-starter-test") // Starter test Spring Boot
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5") // Intègre Kotlin avec JUnit5
	testRuntimeOnly("org.junit.platform:junit-platform-launcher") // Pour lancer les tests
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict") // Gestion de la nullité stricte pour Kotlin
	}
}

allOpen {
	annotation("jakarta.persistence.Entity") // Ouvre les classes annotées @Entity pour Hibernate
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks {
	withType<Test> {
		useJUnitPlatform()
	}

	jar {
		enabled = false
	}

	bootJar {
		archiveFileName.set("app.jar")
		manifest {
			attributes["Start-Class"] = "com.projexio.taskora_back.TaskoraBackApplicationKt"
		}
	}
}
