package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.datamodel.Receipt
import org.springframework.stereotype.Component

/**
 * This class represents a rule for calculating rewards based on the purchase time of a receipt.
 *
 * 10 points if the time of purchase is after 2:00pm and before 4:00pm.
 */
@Component
class PurchaseTimeRule : RewardRule {

    /**
     * Calculates the reward points based on the purchase time of the receipt.
     *
     * @param receipt The receipt for which the reward needs to be calculated.
     * @return The calculated reward as a Long value.
     */
    override fun calculate(receipt: Receipt): Long {
        val purchaseHour = receipt.purchaseTime.hour
        val purchaseMin = receipt.purchaseTime.minute
        return if ((purchaseHour == 15) || (purchaseHour == 14 && purchaseMin > 0))
            10L
        else
            0L
    }
}