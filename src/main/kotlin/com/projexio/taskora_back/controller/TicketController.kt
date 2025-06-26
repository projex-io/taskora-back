package com.projexio.taskora_back.controller

import com.projexio.taskora_back.model.Ticket
import com.projexio.taskora_back.service.TicketService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/tickets")
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
    fun getById(@PathVariable id: Long): ResponseEntity<TicketView> {
        val ticket = ticketService.getById(id)
        return ResponseEntity.ok(TicketView(title = ticket.title, description = ticket.description, status = ticket.status))
    }

    @PostMapping
    fun create(@RequestBody ticket: Ticket): ResponseEntity<TicketView> {
        val createdTicket = ticketService.create(ticket)
        return ResponseEntity.ok(TicketView(title = createdTicket.title, description = createdTicket.description, status = createdTicket.status))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updated: Ticket): ResponseEntity<TicketView> {
        val updatedTicket = ticketService.update(id, updated)
        return ResponseEntity.ok(TicketView(
            title = updatedTicket.title,
            description = updatedTicket.description,
            status = updatedTicket.status
        ))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        ticketService.delete(id)
        return ResponseEntity.ok("Ticket deleted")
    }
}