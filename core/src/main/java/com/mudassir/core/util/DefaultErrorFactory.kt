package com.mudassir.core.util

import android.content.Context
import com.mudassir.core.R
import javax.inject.Inject

class DefaultErrorFactory @Inject constructor(val context: Context) : ErrorFactory  {

    override fun createEmptyErrorMessage(): String {
        return context.getString(R.string.empty_state_message)
    }

    override fun createApiErrorMessage(e: Exception): String {
        return e.message.toString()
    }
}