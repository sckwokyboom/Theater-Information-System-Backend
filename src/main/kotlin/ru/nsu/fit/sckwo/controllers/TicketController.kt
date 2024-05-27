package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.nsu.fit.sckwo.model.entities.Sum
import ru.nsu.fit.sckwo.model.entities.Ticket
import ru.nsu.fit.sckwo.repositories.TicketRepository

@Controller
@RequestMapping("/tickets")
//@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class TicketController @Autowired constructor(private val ticketRepository: TicketRepository) {
    @GetMapping("")
    fun getAllTickets(): ResponseEntity<List<Ticket>> {
        return ResponseEntity.ok(ticketRepository.getAllTickets())
    }

    @GetMapping("/filter")
    fun getFilterTickets(
        @RequestParam(required = false) performanceId: Int?,
        @RequestParam(required = false) isPremiere: Boolean?,
        @RequestParam(required = false) isUpcomingPerformances: Boolean?,
        @RequestParam(required = false) dateOfStart: String?,
        @RequestParam(required = false) dateOfEnd: String?,
        @RequestParam(required = false) isPreSold: Boolean?,
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
        @RequestParam(required = false) performanceId: Int?,
    ): ResponseEntity<List<Sum>> {
        return ResponseEntity.ok(
            ticketRepository.getSumFor(
                performanceId
            )
        )
    }

    @PostMapping
    fun createTicket(@RequestBody ticket: Ticket): ResponseEntity<Ticket> {
        ticketRepository.insert(ticket)
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket)
    }

    @DeleteMapping("/{id}")
    fun deleteTicket(@PathVariable id: Int): ResponseEntity<Void> {
        if (!ticketRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }
        ticketRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}