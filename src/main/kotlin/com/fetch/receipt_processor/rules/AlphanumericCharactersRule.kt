package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.datamodel.Receipt
import org.springframework.stereotype.Component

/**
 * This class represents a rule for calculating the reward points based on retailer name
 *
 * One point is awarded for every alphanumeric character present in the retailer's name.
 */
@Component
class AlphanumericCharactersRule : RewardRule {

    /**
     * Calculates the number of alphanumeric characters in the retailer name of a receipt.
     *
     * @param receipt The receipt for which the reward needs to be calculated.
     * @return The calculated reward as a Long value.
     */
    override fun calculate(receipt: Receipt): Long {
        return receipt.retailer.count { it.isLetterOrDigit() }.toLong()
    }
}