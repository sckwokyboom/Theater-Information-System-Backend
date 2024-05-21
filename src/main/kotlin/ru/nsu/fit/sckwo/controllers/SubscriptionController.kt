package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.nsu.fit.sckwo.model.entities.BuySubscriptionRequest
import ru.nsu.fit.sckwo.model.entities.Subscription
import ru.nsu.fit.sckwo.repositories.SubscriptionRepository
import ru.nsu.fit.sckwo.services.SubscriptionService
import java.time.LocalDate

@Controller
@RequestMapping("/subscriptions")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class SubscriptionController @Autowired constructor(
    private val subscriptionRepository: SubscriptionRepository,
    private val subscriptionService: SubscriptionService,
) {
    @GetMapping("")
    fun getAllSubscriptions(): ResponseEntity<List<Subscription>> {
        return ResponseEntity.ok(subscriptionRepository.getAllSubscriptions())
    }

    @PostMapping("/buy")
    fun buySubscription(@RequestBody request: BuySubscriptionRequest): ResponseEntity<Void> {
        subscriptionService.createSubscriptionWithTickets(request.performanceIds, request.placeIds)
//        subscriptionService.buySubscription(request)
        return ResponseEntity.ok().build()
    }

//    @PostMapping("/create")
//    fun createSubscriptionWithTickets(@RequestBody request: CreateSubscriptionRequest): ResponseEntity<Subscription> {
//        val subscription = Subscription(id = null, price = request.price)
//        val tickets = request.tickets.map {
//            Ticket(
//                id = null,
//                performanceId = it.performanceId,
//                price = it.price,
//                placeId = it.placeId,
//                subscriptionId = null,
//                saleDate = it.saleDate
//            )
//        }
//        val savedSubscription = subscriptionService.createSubscriptionWithTickets(subscription, tickets)
//        return ResponseEntity.ok(savedSubscription)
//    }

    @PostMapping("/createWithTickets")
    fun createSubscriptionWithTickets(
        @RequestParam subPrice: Double,
        @RequestParam ticketData: String,
    ): ResponseEntity<String> {
        try {
            subscriptionService.createSubscriptionWithTicketsInDb(subPrice, ticketData)
            return ResponseEntity.ok("Subscription with tickets created successfully.")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create subscription with tickets: ${e.message}")
        }
    }

    data class CreateSubscriptionRequest(
        val tickets: List<TicketRequest>,
        val price: Double,
    )

    data class TicketRequest(
        val performanceId: Int,
        val price: Double,
        val placeId: Int,
        val saleDate: LocalDate,
    )
}