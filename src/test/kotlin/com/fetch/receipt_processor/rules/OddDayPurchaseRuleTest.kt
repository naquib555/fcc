package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.TestDataGenerator.sampleReceipt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class OddDayPurchaseRuleTest {

    private val instance = OddDayPurchaseRule()

    @Test
    fun testCalculateWithOddDayInPurchaseDate() {
        val receipt = sampleReceipt(purchaseDate = LocalDate.of(2023, 3, 3))
        val result = instance.calculate(receipt)
        assertEquals(6L, result)
    }

    @Test
    fun testCalculateWithEvenDayInPurchaseDate() {
        val receipt = sampleReceipt(purchaseDate = LocalDate.of(2023, 3, 4))
        val result = instance.calculate(receipt)
        assertEquals(0L, result)
    }
}