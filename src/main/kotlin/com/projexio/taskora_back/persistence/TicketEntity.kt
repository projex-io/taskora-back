package com.projexio.taskora_back.persistence

import com.projexio.taskora_back.model.Ticket
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tickets")
data class TicketEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String = "",

    var description: String = "",

    var status: String = "TODO" // TODO, IN_PROGRESS, DONE
){
    fun toTicket() = Ticket(title = title, description = description, status = status)
}