package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.datamodel.AppException
import com.fetch.receipt_processor.datamodel.Receipt
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * This class represents a rule for calculating the reward based on the item description.
 *
 * If the trimmed length of the item description is a multiple of 3, multiply the price
 * by 0.2 and round up to the nearest integer.
 */
@Component
class ItemDescriptionRule : RewardRule {
    private val rate = BigDecimal("0.2")

    /**
     * Calculates the reward based on the item description.
     *
     * @param receipt The receipt for which the reward needs to be calculated.
     * @return The calculated reward as a Long value.
     * @throws AppException if the calculated reward cannot be represented as int64.
     */
    override fun calculate(receipt: Receipt): Long {
        return receipt.items.sumOf { item ->
            val lengthOfDescription = item.shortDescription.trim().length
            if (lengthOfDescription % 3 == 0) {
                val amount = BigDecimal(item.price)
                    .multiply(rate)
                    .setScale(0, RoundingMode.CEILING)
                try {
                    amount.longValueExact()
                } catch (e: ArithmeticException) {
                    throw AppException("The calculated reward cannot be exactly represented as int64")
                }
            } else
                0L
        }
    }
}
