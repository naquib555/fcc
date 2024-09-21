package com.fetch.receipt_processor

import com.fetch.receipt_processor.datamodel.Item
import com.fetch.receipt_processor.datamodel.Receipt
import java.time.LocalDate
import java.time.LocalTime

object TestDataGenerator {

    fun sampleReceipt(
        retailer: String = "Retailer12",
        purchaseDate: LocalDate = LocalDate.now(),
        purchaseTime: LocalTime = LocalTime.now(),
        items: List<Item> = listOf(
            Item(
                shortDescription = "Item1",
                price = "5.99"
            )
        ),
        total: String = "5.99"
    ) : Receipt {
        return Receipt(
            retailer = retailer,
            purchaseDate = purchaseDate,
            purchaseTime = purchaseTime,
            items = items,
            total = total
        )
    }
}