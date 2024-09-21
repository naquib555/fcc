package com.fetch.receipt_processor.datamodel

import jakarta.validation.Validation
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ItemTest {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun testValidItem() {
        val item = Item(
            shortDescription = "Test Item",
            price = "10.99"
        )

        val violations = validator.validate(item)
        assertTrue(violations.isEmpty())
    }

    @Test
    fun testInvalidItemDescription() {
        val item = Item(
            shortDescription = "###Invalid Item^%",
            price = "10.00"
        )

        val violations = validator.validate(item)

        assertTrue(violations.isNotEmpty())
        assertEquals(violations.size, 1)
    }

    @Test
    fun testInvalidItemPrice() {
        val item = Item(
            shortDescription = "Test Item",
            price = "0.9"
        )

        val violations = validator.validate(item)

        assertTrue(violations.isNotEmpty())
        assertEquals(violations.size, 1)
    }

    @Test
    fun testInvalidItemPriceWithNegative() {
        val item = Item(
            shortDescription = "Test Item",
            price = "-50.09"
        )

        val violations = validator.validate(item)

        assertTrue(violations.isNotEmpty())
        assertEquals(violations.size, 1)
    }


}