package com.projexio.taskora_back.service

import com.projexio.taskora_back.model.Ticket
import com.projexio.taskora_back.model.Tickets
import com.projexio.taskora_back.persistence.TicketEntity
import com.projexio.taskora_back.persistence.TicketRepository
import org.springframework.stereotype.Service

@Service
class TicketService(
    private val ticketRepository: TicketRepository
) {
    fun getAll() : Tickets {
        val entities = ticketRepository.findAll()
        return entities.map { it.toTicket() }.toSet()
    }

    fun getById(id: Long) : Ticket {
        val entity = ticketRepository.findById(id)
        return entity.map { it.toTicket() }.orElseThrow()
    }

    fun create(ticket: Ticket) : Ticket {
        val entity = TicketEntity(title = ticket.title, description = ticket.description)
        return ticketRepository
            .save(entity)
            .toTicket()
    }

    fun update(id: Long, ticket: Ticket) : Ticket {
        val entity = ticketRepository.findById(id)
        entity.ifPresent {
            it.title = ticket.title
            it.description = ticket.description

        }
        return ticketRepository.save(entity.get()).toTicket()
    }

    fun delete(id : Long) {
        ticketRepository.deleteById(id)
    }
}