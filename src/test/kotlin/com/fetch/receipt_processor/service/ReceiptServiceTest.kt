package com.fetch.receipt_processor.service

import com.fetch.receipt_processor.TestDataGenerator.sampleReceipt
import com.fetch.receipt_processor.rules.RewardRule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.BDDMockito.given
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID
import kotlin.test.assertNull
import kotlin.test.assertTrue

@SpringBootTest
class ReceiptServiceTest {

    private lateinit var rewardRules: List<RewardRule>

    private lateinit var instance: ReceiptService

    @BeforeEach
    fun setup() {
        openMocks(this)
        rewardRules = listOf(mock(RewardRule::class.java))
        instance = ReceiptService(rewardRules)
    }

    @Test
    fun testCalculatePoints() {
        val receipt = sampleReceipt()
        given(rewardRules.sumOf { it.calculate(receipt) }).willReturn(12)
        val result = instance.calculatePoints(receipt)
        assertTrue(
            try {
                UUID.fromString(result)
                true
            } catch (exception: IllegalArgumentException) {
                false
            }
        )
    }

    @Test
    fun testFetchPoints() {
        val receipt = sampleReceipt()
        given(rewardRules.sumOf { it.calculate(receipt) }).willReturn(12)
        val result = instance.calculatePoints(receipt)
        assertEquals(12L, instance.fetchPoints(result))
    }

    @Test
    fun testFetchPointsForInvalidReceipt() {
        val points = instance.fetchPoints("invalidId")
        assertNull(points)
    }
}