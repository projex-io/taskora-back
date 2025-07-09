package com.projexio.taskora_back.controller

import com.projexio.taskora_back.service.TicketService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Tickets", description = "Endpoints de gestion des tickets")
class TicketController(private val ticketService: TicketService) {

    @GetMapping
    fun getAll(): ResponseEntity<Set<TicketView>> {
        val tickets = ticketService.getAll()
        return ResponseEntity.ok(
            tickets.map {
                TicketView(title = it.title, description = it.description, status = it.status)
            }.toSet()
        )
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Récupérer un ticket par son ID",
        description = "Retourne un ticket unique identifié par son ID.",
        responses = [
            ApiResponse(responseCode = "200", description = "Ticket trouvé"),
            ApiResponse(responseCode = "404", description = "Ticket non trouvé")
        ]
    )
    fun getById(
        @Parameter(description = "ID du ticket à récupérer", example = "123", required = true)
        @PathVariable id: Long
    ): ResponseEntity<TicketView> {
        val ticket = ticketService.getById(id)
        return ResponseEntity.ok(TicketView(title = ticket.title, description = ticket.description, status = ticket.status))
    }

    @PostMapping
    @Operation(summary = "Créer un ticket")
    @SwaggerRequestBody(
        description = "Requête de création d'un ticket",
        required = true,
        content = [Content(
            mediaType = "application/json",
            examples = [ExampleObject(
                name = "Exemple de ticket",
                value = """{
                    "title": "Problème de connexion",
                    "description": "Impossible de se connecter avec l'utilisateur X",
                    "status": "OPEN"
                }"""
            )]
        )]
    )
    fun create(@RequestBody ticket: TicketView): ResponseEntity<TicketView> {
        val createdTicket = ticketService.create(ticket.toTicket())
        return ResponseEntity.ok(TicketView(title = createdTicket.title, description = createdTicket.description, status = createdTicket.status))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updated: TicketView): ResponseEntity<TicketView> {
        val updatedTicket = ticketService.update(id, updated.toTicket())
        return ResponseEntity.ok(TicketView(
            title = updatedTicket.title,
            description = updatedTicket.description,
            status = updatedTicket.status
        ))
    }

    @DeleteMapping("/{id}")
    //@SecurityRequirement(name = "bearerAuth")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        ticketService.delete(id)
        return ResponseEntity.ok("Ticket deleted")
    }
}