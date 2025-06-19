package com.projexio.taskora_back.service

import com.projexio.taskora_back.model.Ticket
import com.projexio.taskora_back.persistence.TicketEntity
import com.projexio.taskora_back.persistence.TicketRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class TicketServiceTest {

    @RelaxedMockK
    private lateinit var ticketRepository: TicketRepository

    @InjectMockKs
    private lateinit var ticketService: TicketService

    @Test
    fun `getAll should return all tickets`() {
        val ticketEntity1 = TicketEntity(title = "Ticket 1", description = "Description 1")
        val ticketEntity2 = TicketEntity(title = "Ticket 2", description = "Description 2")

        every { ticketRepository.findAll() } returns listOf(ticketEntity1, ticketEntity2)

        val result = ticketService.getAll()

        assertEquals(2, result.size)
    }

    @Test
    fun `getById should return the correct ticket`() {
        val ticketEntity = TicketEntity(id = 1L, title = "Ticket 1", description = "Description 1")
        every { ticketRepository.findById(1L) } returns Optional.of(ticketEntity)

        val result = ticketService.getById(1L)

        assertNotNull(result)
        assertEquals("Ticket 1", result.title)
        assertEquals("Description 1", result.description)
    }

    @Test
    fun `getById should throw exception if ticket does not exist`() {
        every { ticketRepository.findById(1L) } returns Optional.empty()

        assertThrows(NoSuchElementException::class.java) {
            ticketService.getById(1L)
        }
    }

    @Test
    fun `create should save and return the new ticket`() {
        val ticket = Ticket(title = "New Ticket", description = "New Description")
        val ticketEntity = TicketEntity(id = null, title = "New Ticket", description = "New Description")
        every { ticketRepository.save(ticketEntity) } returns ticketEntity

        val result = ticketService.create(ticket)

        assertNotNull(result)
        assertEquals("New Ticket", result.title)
        assertEquals("New Description", result.description)
    }

    @Test
    fun `update should update and return the ticket`() {
        val ticket = Ticket(title = "Updated Title", description = "Updated Description")
        val ticketEntity = TicketEntity(id = null, title = "Old Title", description = "Old Description")

        every { ticketRepository.findById(1L) } returns Optional.of(ticketEntity)
        every { ticketRepository.save(ticketEntity) } returns ticketEntity

        val result = ticketService.update(1L, ticket)

        assertNotNull(result)
        assertEquals("Updated Title", result.title)
        assertEquals("Updated Description", result.description)
    }

    @Test
    fun `update should throw exception if ticket does not exist`() {
        val ticket = Ticket(title = "Updated Title", description = "Updated Description")
        every { ticketRepository.findById(1L) } returns Optional.empty()

        assertThrows(NoSuchElementException::class.java) {
            ticketService.update(1L, ticket)
        }
    }

    @Test
    fun `delete should remove the ticket`() {
        ticketService.delete(1L)

        verify { ticketRepository.deleteById(1L) }
    }

    @Test
    fun `this test should fail`(){
        assertEquals(true, false)
    }
}