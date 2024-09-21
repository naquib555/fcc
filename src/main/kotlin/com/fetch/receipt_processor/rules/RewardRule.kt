package com.fetch.receipt_processor.rules

import com.fetch.receipt_processor.datamodel.Receipt

/**
 * Represents a rule for calculating rewards defined by the implemented classes.
 */
interface RewardRule {
    fun calculate(receipt: Receipt): Long
}