package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.TestDataGenerator.sampleReceipt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AlphanumericCharactersRuleTest {

    private val instance = AlphanumericCharactersRule()

    @Test
    fun testCalculate() {
        val receipt = sampleReceipt()
        val result = instance.calculate(receipt)
        assertEquals(10L, result)
    }

    @Test
    fun testCalculateWithSpecialCharactersInRetailer() {
        val receipt = sampleReceipt(retailer = "Retailer!@#")
        val result = instance.calculate(receipt)
        assertEquals(8L, result)
    }

    @Test
    fun testCalculateWithSpacesInRetailer() {
        val receipt = sampleReceipt(retailer = "    Retailer12          50       ")
        val result = instance.calculate(receipt)
        assertEquals(12L, result)
    }
}