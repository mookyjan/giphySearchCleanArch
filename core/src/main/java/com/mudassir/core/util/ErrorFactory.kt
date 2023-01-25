package com.mudassir.core.util

interface  ErrorFactory {
    fun createEmptyErrorMessage(): String
    fun createApiErrorMessage(e: Exception): String
}