package com.fetch.receipt_processor.datamodel

/**
 * Custom exception class for representing exceptions specific to the application.
 *
 * @param message The detail message of the exception.
 */
class AppException(message: String?) : RuntimeException(message)