package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.datamodel.Receipt
import org.springframework.stereotype.Component

/**
 * This class represents a rule for calculating the reward based on the quantity of items in a receipt.
 *
 * 5 points for every two items on the receipt.
 */
@Component
class ItemQuantityRule : RewardRule {
    private val rate = 5L

    /**
     * Calculates the reward based on the quantity of items in a receipt.
     *
     * @param receipt The receipt for which the reward needs to be calculated.
     * @return The calculated reward as a Long value.
     */
    override fun calculate(receipt: Receipt): Long {
        val dividedValue = receipt.items.size / 2
        return dividedValue * rate
    }
}