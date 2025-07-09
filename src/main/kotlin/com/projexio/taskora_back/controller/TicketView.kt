package com.projexio.taskora_back.controller

import com.projexio.taskora_back.model.Ticket
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Représentation simplifiée d'un ticket")
data class TicketView(
    @field:Schema(description = "Titre du ticket", example = "Bug critique")
    val title: String = "",
    @field:Schema(description = "Description du ticket")
    val description: String = "",
    @field:Schema(description = "Statut du ticket")
    val status: String = "TODO"
){
    fun toTicket() = Ticket(title = title, description = description, status = status)
}
