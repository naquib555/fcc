package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.TestDataGenerator.sampleReceipt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalTime

class PurchaseTimeRuleTest {

    private val instance = PurchaseTimeRule()

    @Test
    fun testCalculateWithPurchaseTimeAt2PM() {
        val receipt = sampleReceipt(purchaseTime = LocalTime.of(14, 0))
        val result = instance.calculate(receipt)
        assertEquals(0L, result)
    }

    @Test
    fun testCalculateWithPurchaseTimeJustAfter2PM() {
        val receipt = sampleReceipt(purchaseTime = LocalTime.of(14, 1))
        val result = instance.calculate(receipt)
        assertEquals(10L, result)
    }

    @Test
    fun testCalculateWithPurchaseTimeAt3PM() {
        val receipt = sampleReceipt(purchaseTime = LocalTime.of(15, 0))
        val result = instance.calculate(receipt)
        assertEquals(10L, result)
    }

    @Test
    fun testCalculateWithPurchaseTimeJustBefore2PM() {
        val receipt = sampleReceipt(purchaseTime = LocalTime.of(13, 59))
        val result = instance.calculate(receipt)
        assertEquals(0L, result)
    }

    @Test
    fun testCalculateWithPurchaseTimeJustAfter3PM() {
        val receipt = sampleReceipt(purchaseTime = LocalTime.of(15, 1))
        val result = instance.calculate(receipt)
        assertEquals(10L, result)
    }

    @Test
    fun testCalculateWithPurchaseTimeAt4PM() {
        val receipt = sampleReceipt(purchaseTime = LocalTime.of(16, 0))
        val result = instance.calculate(receipt)
        assertEquals(0L, result)
    }

    @Test
    fun testCalculateWithPurchaseTimeJustBefore4PM() {
        val receipt = sampleReceipt(purchaseTime = LocalTime.of(15, 59))
        val result = instance.calculate(receipt)
        assertEquals(10L, result)
    }



    @Test
    fun testCalculateWithPurchaseTimeAtMidnight() {
        val receipt = sampleReceipt(purchaseTime = LocalTime.MIDNIGHT)
        val result = instance.calculate(receipt)
        assertEquals(0L, result)
    }

    @Test
    fun testCalculateWithPurchaseTimeAtNoon() {
        val receipt = sampleReceipt(purchaseTime = LocalTime.NOON)
        val result = instance.calculate(receipt)
        assertEquals(0L, result)
    }
}