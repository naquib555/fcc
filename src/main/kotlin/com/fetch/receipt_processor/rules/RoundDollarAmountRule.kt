package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.datamodel.Receipt
import org.springframework.stereotype.Component

/**
 * This class represents a rule for calculating rewards based on the total amount of the receipt
 *
 * 50 points if the total is a round dollar amount with no cents.
 */
@Component
class RoundDollarAmountRule : RewardRule {

    /**
     * Calculates the reward points based on the total amount of the receipt.
     *
     * @param receipt The receipt for which the reward needs to be calculated.
     * @return The calculated reward as a Long value.
     */
    override fun calculate(receipt: Receipt): Long {
        return if (receipt.total.endsWith(".00")) {
            50L
        } else {
            0L
        }
    }
}