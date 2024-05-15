package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.nsu.fit.sckwo.model.entitiies.Ticket
import ru.nsu.fit.sckwo.repositories.TicketRepository

@Controller
@RequestMapping("/tickets")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class TicketController @Autowired constructor(private val ticketRepository: TicketRepository) {
    @GetMapping("/all")
    fun getAllPlays(): ResponseEntity<List<Ticket>> {
        return ResponseEntity.ok(ticketRepository.getAllTickets())
    }
}