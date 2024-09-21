package com.fetch.receipt_processor.controller

import com.fetch.receipt_processor.datamodel.AppException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


/**
 * Class GlobalExceptionController handles global exceptions and provides error details in the form of ResponseEntity.
 */
@RestControllerAdvice
class GlobalExceptionController {

    /**
     * Handles MethodArgumentNotValidException and returns a ResponseEntity with error details.
     *
     * @param exception The MethodArgumentNotValidException to handle
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors = exception.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
        return ResponseEntity.badRequest().body(mapOf("error" to "The receipt is invalid", "errors" to errors))
    }

    /**
     * Handles an AppException and returns a ResponseEntity with error details.
     *
     * @param exception The AppException to handle
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(AppException::class)
    fun handleAppException(exception: AppException): ResponseEntity<Any> {
        return ResponseEntity.badRequest().body(mapOf("error" to exception.message))
    }
}