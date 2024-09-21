package com.fetch.receipt_processor.datamodel

import jakarta.validation.constraints.Pattern

/**
 * Represents an item in a receipt for a purchase.
 *
 * @property shortDescription The description of the item. Must contain only alphanumeric characters, spaces, and -.
 * @property price The price of the item. Must be in the format "x.xx" where x represents digits.
 */
data class Item(
    @field:Pattern(regexp = "^[\\w\\s\\-]+$")
    val shortDescription: String,

    @field:Pattern(regexp = "^\\d+\\.\\d{2}$")
    val price: String
)