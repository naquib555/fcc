package com.fetch.receipt_processor.datamodel

import jakarta.validation.Validation
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime
import kotlin.test.assertTrue

class ReceiptTest {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun testValidReceipt() {
        val receipt = Receipt(
            retailer = "Test Retailer",
            purchaseDate = LocalDate.now(),
            purchaseTime = LocalTime.now(),
            items = listOf(Item("item", "10.99")),
            total = "10.99"
        )

        val violations = validator.validate(receipt)

        assertTrue(violations.isEmpty(), "Expected no violations for a valid Receipt.")
    }

    @Test
    fun testInvalidReceipt() {
        val receipt = Receipt(
            retailer = "!Invalid Retailer^%",
            purchaseDate = LocalDate.now(),
            purchaseTime = LocalTime.now(),
            items = listOf(),
            total = "12,34"
        )

        val violations = validator.validate(receipt)

        assertTrue(violations.isNotEmpty(), "Expected violations for an invalid Receipt.")
    }
}