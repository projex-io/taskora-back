package com.projexio.taskora_back.controller

data class TicketView(
    val title: String = "",
    val description: String = "",
    val status: String = "TODO"
)
