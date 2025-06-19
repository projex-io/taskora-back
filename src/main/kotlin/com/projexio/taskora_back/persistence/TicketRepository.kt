package com.projexio.taskora_back.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository : JpaRepository<TicketEntity, Long> {
}