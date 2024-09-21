package com.fetch.receipt_processor.controller

import com.fetch.receipt_processor.datamodel.Receipt
import com.fetch.receipt_processor.service.ReceiptService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fetch.receipt_processor.TestDataGenerator.sampleReceipt
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.mockito.BDDMockito.given
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@WebMvcTest(ReceiptsController::class)
class ReceiptsControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var receiptService: ReceiptService

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun testProcessReceipt() {
        val receipt = sampleReceipt()
        val receiptId = UUID.randomUUID().toString()

        given(receiptService.calculatePoints(receipt)).willReturn(receiptId)
        mockMvc.perform(
            post("/receipts/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(receipt))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(receiptId))
    }

    @Test
    fun testInvalidProcessReceipt() {
        val receipt = Receipt(
            "Retailer", LocalDate.now(), LocalTime.now(),
            emptyList(), "10.00"
        )
        val receiptId = "receipt-1"

        given(receiptService.calculatePoints(receipt)).willReturn(receiptId)

        mockMvc.perform(
            post("/receipts/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(receipt))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error").value("The receipt is invalid"))
    }

    @Test
    fun testGetReceiptPoints() {
        val receiptId = "receipt-1"
        val points = 100L

        given(receiptService.fetchPoints(receiptId)).willReturn(points)

        mockMvc.perform(
            get("/receipts/$receiptId/points")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.points").value(points))
    }

    @Test
    fun testGetReceiptPointsWithInvalidReceiptId() {
        val receiptId = "invalid"

        given(receiptService.fetchPoints(receiptId)).willReturn(null)

        mockMvc.perform(
            get("/receipts/$receiptId/points")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
    }
}