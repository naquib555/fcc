package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.datamodel.Receipt
import org.springframework.stereotype.Component

/**
 * This class represents a rule for calculating rewards based on the purchase date of a receipt.
 *
 * 6 points if the day in the purchase date is odd.
 */
@Component
class OddDayPurchaseRule : RewardRule {

    /**
     * Calculates the reward based on the purchase date of a receipt.
     *
     * @param receipt The receipt for which the reward needs to be calculated.
     * @return The calculated reward as a Long value.
     */
    override fun calculate(receipt: Receipt): Long {
        return if (receipt.purchaseDate.dayOfMonth % 2 != 0) 6L else 0L
    }
}