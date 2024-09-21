package com.fetch.receipt_processor.service

import com.fetch.receipt_processor.datamodel.Receipt
import com.fetch.receipt_processor.rules.RewardRule
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

/**
 * The ReceiptService class calculates and fetches reward points for a given receipt.
 *
 * @property rewardRules The list of reward rules to calculate the points.
 * @property pointsMap The mapping of receipt ID to reward points.
 */
@Service
class ReceiptService(private val rewardRules: List<RewardRule>) {

    // in-memory storage for calculated points
    private val pointsMap = ConcurrentHashMap<String, Long>()

    /**
     * Calculate the reward points for a given receipt.
     *
     * @param receipt The receipt object containing the details of the purchase.
     * @return The unique ID of the calculated points.
     */
    fun calculatePoints(receipt: Receipt): String {
        val points = rewardRules.sumOf { rule ->
            rule.calculate(receipt)
        }
        val pointsId = UUID.randomUUID().toString()
        pointsMap[pointsId] = points
        return pointsId
    }

    /**
     * Fetches the reward points for a given receipt ID.
     *
     * @param receiptId The unique ID of the receipt.
     * @return The reward points for the given receipt ID, or null if no points are found.
     */
    fun fetchPoints(receiptId: String): Long? {
        return pointsMap[receiptId]
    }
}