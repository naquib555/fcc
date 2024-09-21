package com.fetch.receipt_processor.controller

import com.fetch.receipt_processor.datamodel.Receipt
import com.fetch.receipt_processor.service.ReceiptService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * The ReceiptsController class is responsible for handling HTTP requests related to receipts.
 *
 * @property receiptService The service class that calculates and fetches reward points for receipts.
 */
@RestController
@RequestMapping("receipts")
class ReceiptsController(private val receiptService: ReceiptService) {

    /**
     * Calculates the reward points for the given receipt.
     *
     * @param receipt The receipt object containing the details of the purchase.
     * @return A ResponseEntity object containing a map with the unique ID of the calculated points.
     */
    @PostMapping("process")
    fun processReceipt(@Valid @RequestBody receipt: Receipt): ResponseEntity<Map<String, String>> {
        val response = mapOf("id" to receiptService.calculatePoints(receipt))
        return ResponseEntity.ok(response)
    }

    /**
     * Retrieves the reward points for a given receipt ID.
     *
     * @param id The unique ID of the receipt to retrieve the points for.
     * @return A ResponseEntity object containing the "points". If no points found then 404 is sent
     */
    @GetMapping("{id}/points")
    fun getReceiptPoints(@PathVariable id: String): ResponseEntity<Map<String, Long>> {
        val points = receiptService.fetchPoints(id)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        val response = mapOf("points" to points)
        return ResponseEntity.ok(response)
    }
}