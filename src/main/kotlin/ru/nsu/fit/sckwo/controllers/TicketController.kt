package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.nsu.fit.sckwo.model.entitiies.Sum
import ru.nsu.fit.sckwo.model.entitiies.Ticket
import ru.nsu.fit.sckwo.repositories.TicketRepository

@Controller
@RequestMapping("/tickets")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class TicketController @Autowired constructor(private val ticketRepository: TicketRepository) {
    @GetMapping("")
    fun getAllTickets(): ResponseEntity<List<Ticket>> {
        return ResponseEntity.ok(ticketRepository.getAllTickets())
    }

    @GetMapping("/filter")
    fun getFilterTickets(
        @RequestParam(required = false) performanceId: Int?,
        @RequestParam(required = false) isPremiere: String?,
        @RequestParam(required = false) isUpcomingPerformances: String?,
        @RequestParam(required = false) dateOfStart: String?,
        @RequestParam(required = false) dateOfEnd: String?,
        @RequestParam(required = false) isPreSold: String?,
    ): ResponseEntity<List<Ticket>> {
        return ResponseEntity.ok(
            ticketRepository.getTickets(
                performanceId,
                isPremiere,
                isUpcomingPerformances,
                dateOfStart,
                dateOfEnd,
                isPreSold
            )
        )
    }

    @GetMapping("/sum")
    fun getSum(
        @RequestParam(required = true) performanceId: Int,
    ): ResponseEntity<List<Sum>> {
        return ResponseEntity.ok(
            ticketRepository.getSumFor(
                performanceId
            )
        )
    }
}