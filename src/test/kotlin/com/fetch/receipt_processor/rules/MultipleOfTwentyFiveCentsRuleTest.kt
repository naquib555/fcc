package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.TestDataGenerator.sampleReceipt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MultipleOfTwentyFiveCentsRuleTest {

    private val instance = MultipleOfTwentyFiveCentsRule()

    @Test
    fun testCalculateWithTotalEnding00() {
        val receipt = sampleReceipt(total = "42.00")
        val result = instance.calculate(receipt)
        assertEquals(25L, result)
    }

    @Test
    fun testCalculateWithTotalEnding25() {
        val receipt = sampleReceipt(total = "42.25")
        val result = instance.calculate(receipt)
        assertEquals(25L, result)
    }

    @Test
    fun testCalculateWithTotalEnding50() {
        val receipt = sampleReceipt(total = "42.50")
        val result = instance.calculate(receipt)
        assertEquals(25L, result)
    }

    @Test
    fun testCalculateWithTotalEnding75() {
        val receipt = sampleReceipt(total = "42.75")
        val result = instance.calculate(receipt)
        assertEquals(25L, result)
    }

    @Test
    fun testCalculateWithTotalEnding99() {
        val receipt = sampleReceipt(total = "42.99")
        val result = instance.calculate(receipt)
        assertEquals(0L, result)
    }

}