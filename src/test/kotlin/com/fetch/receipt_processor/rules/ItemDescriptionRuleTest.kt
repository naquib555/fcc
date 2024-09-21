package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.TestDataGenerator.sampleReceipt
import com.fetch.receipt_processor.datamodel.AppException
import com.fetch.receipt_processor.datamodel.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class ItemDescriptionRuleTest {

    private val instance = ItemDescriptionRule()

    @Test
    fun testCalculateWithDescriptionMultipleOfThree() {
        val items = listOf(Item(shortDescription = "abc", price = "100.00"))
        val receipt = sampleReceipt(items = items)
        val result = instance.calculate(receipt)
        assertEquals(BigDecimal(20).toLong(), result)
    }

    @Test
    fun testCalculateWithDescriptionNotMultipleOfThree() {
        val items = listOf(Item(shortDescription = "abcd", price = "100.00"))
        val receipt = sampleReceipt(items = items)
        val result = instance.calculate(receipt)
        assertEquals(BigDecimal(0).toLong(), result)
    }

    @Test
    fun testCalculateWithSpacesInDescription() {
        val items = listOf(
            Item(shortDescription = " a   bc", price = "100.00"),
            Item(shortDescription = "abc  ", price = "50.50"),
            Item(shortDescription = "  a   b   c d", price = "80.00")
        )
        val receipt = sampleReceipt(items = items)
        val result = instance.calculate(receipt)
        assertEquals(BigDecimal(31).toLong(), result)
    }

    @Test
    fun testCalculateWithMultipleItems() {
        val items = listOf(
            Item(shortDescription = "abc", price = "1000.99"),
            Item(shortDescription = "abc", price = "999.99"),
            Item(shortDescription = "abc", price = "1.01"),
            Item(shortDescription = "abc", price = "1.00"),
            Item(shortDescription = "abc", price = "0.99"),
            Item(shortDescription = "abcd", price = "1.00")
        )
        val receipt = sampleReceipt(items = items)
        val result = instance.calculate(receipt)
        assertEquals(404L, result)
    }

    @Test
    fun testCalculateWithOverflowValue() {
        val items = listOf(Item(shortDescription = "abc", price = "1E20"))
        val receipt = sampleReceipt(items = items)
        assertThrows<AppException> {
            instance.calculate(receipt)
        }
    }
}