package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.TestDataGenerator.sampleReceipt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoundDollarAmountRuleTest {
    private val instance = RoundDollarAmountRule()

    @Test
    fun testCalculateWithAmountEndingInDot00() {
        val receipt = sampleReceipt(total = "100.00")
        val result = instance.calculate(receipt)
        assertEquals(50L, result)
    }

    @Test
    fun testCalculateWithAmountEndingInDot50() {
        val receipt = sampleReceipt(total = "100.50")
        val result = instance.calculate(receipt)
        assertEquals(0L, result)
    }

    @Test
    fun testCalculateWithZeroAmount() {
        val receipt = sampleReceipt(total = "0.00")
        val result = instance.calculate(receipt)
        assertEquals(50L, result)
    }
}