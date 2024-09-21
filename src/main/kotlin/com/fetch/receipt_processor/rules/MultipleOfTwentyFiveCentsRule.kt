package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.datamodel.Receipt
import org.springframework.stereotype.Component
import java.math.BigDecimal

/**
 * This class represents a rule for calculating rewards based on the total amount of a receipt.
 *
 * 25 points if the total is a multiple of 0.25
 */
@Component
class MultipleOfTwentyFiveCentsRule : RewardRule {
    private val quarterValue = BigDecimal("0.25")

    /**
     * Calculates the reward points based on the total amount of a receipt.
     *
     * @param receipt The receipt for which the reward needs to be calculated.
     * @return The calculated reward as a Long value.
     */
    override fun calculate(receipt: Receipt): Long {
        val cents = receipt.total.takeLast(2)
        return if (cents == "00" || cents == "25" || cents == "50" || cents == "75") {
            25L
        } else {
            0L
        }
    }
}