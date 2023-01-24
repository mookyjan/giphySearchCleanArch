package com.mudassir.core

import android.content.Context
import android.view.View
import android.view.View.*
import android.view.inputmethod.InputMethodManager


var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

fun View.hide(gone: Boolean = true) {
    visibility = if (gone) GONE else INVISIBLE
}

fun View.show() {
    visibility = VISIBLE
}


fun View.showKeyboard(): Boolean {
    this.requestFocus()
    val inputMethodManager: InputMethodManager? =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    return inputMethodManager?.showSoftInput(this, 0) ?: false
}

fun View.hideKeyboard(): Boolean {
    this.clearFocus()
    val inputMethodManager: InputMethodManager? =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    return inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0) ?: false
}

