package com.projexio.taskora_back.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/public")
class PublicController {

    @GetMapping
    fun greeting() : ResponseEntity<String> {
        return ResponseEntity.ok("Hello World!")
    }
}