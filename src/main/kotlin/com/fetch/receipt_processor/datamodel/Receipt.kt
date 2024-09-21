package com.fetch.receipt_processor.datamodel

import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalTime


/**
 * Represents a receipt for a purchase.
 *
 * @property retailer The name of the retailer. Must contain only alphanumeric characters, spaces, &, and -.
 * @property purchaseDate The date when the purchase was made.
 * @property purchaseTime The time when the purchase was made.
 * @property items A list of items purchased. Must have at least one item.
 * @property total The total amount of the purchase. Must be in the format "x.xx" where x represents digits.
 */
data class Receipt(
    @field:Pattern(regexp = "^[\\w\\s\\-&]+$")
    val retailer: String,

    val purchaseDate: LocalDate,

    val purchaseTime: LocalTime,

    @field:Size(min = 1)
    val items: List<@Valid Item>,

    @field:Pattern(regexp = "^\\d+\\.\\d{2}$")
    val total: String
)

