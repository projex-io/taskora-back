package com.projexio.taskora_back.bootstrap

import com.projexio.taskora_back.AppProperties
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class LogAppProperties(
    private val appProperties: AppProperties
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        println("==== Application Properties ====")
        println("Environment: ${appProperties.env}")
        // Ajoute ici d'autres propriétés si besoin
        println("================================")
    }
}