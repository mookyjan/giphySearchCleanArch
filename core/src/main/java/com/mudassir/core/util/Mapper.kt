package com.mudassir.core.util

interface Mapper <in T, out R> {
    suspend operator fun invoke (input: T): R
}