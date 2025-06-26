package com.projexio.taskora_back

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component


@SpringBootApplication
class TaskoraBackApplication

fun main(args: Array<String>) {
	runApplication<TaskoraBackApplication>(*args)
}

@Component
@ConfigurationProperties(prefix = "app")
data class AppProperties(
	var env: String = "N/A"
)
