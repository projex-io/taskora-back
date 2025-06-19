package com.projexio.taskora_back.model

data class Ticket(
    val title: String = "",
    val description: String = "",
    val status: String = "TODO"
)

typealias Tickets = Collection<Ticket>