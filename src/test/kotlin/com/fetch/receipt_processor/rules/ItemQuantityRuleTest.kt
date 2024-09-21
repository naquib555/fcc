package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.TestDataGenerator.sampleReceipt
import com.fetch.receipt_processor.datamodel.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ItemQuantityRuleTest {

    private val instance = ItemQuantityRule()

    @Test
    fun testCalculateForEvenNumberOfItems() {
        val receiptWithEvenItems = sampleReceipt(
            items = listOf(
                Item("test", "10.00"),
                Item("test", "10.00"),
                Item("test", "10.00"),
                Item("test", "10.00")
            )
        )
        val result = instance.calculate(receiptWithEvenItems)
        assertEquals(10L, result)
    }

    @Test
    fun testCalculateForOddNumberOfItems() {
        val receiptWithOddItems = sampleReceipt(
            items = listOf(
                Item("test", "10.00"),
                Item("test", "10.00"),
                Item("test", "10.00"),
                Item("test", "10.00"),
                Item("test", "10.00")
            )
        )
        val result = instance.calculate(receiptWithOddItems)
        assertEquals(10L, result)
    }
}