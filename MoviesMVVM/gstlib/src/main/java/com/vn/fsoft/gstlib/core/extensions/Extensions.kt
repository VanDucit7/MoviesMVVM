package com.vn.fsoft.gstlib.core.extensions

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.toInt(): Int {
    return when {
        this.text.toString().isBlank() -> 0
        else -> this.text.toString().toInt()
    }
}


fun String.wrapInQuotes(): String {
    var formattedConfigString: String = this
    if (!startsWith("\"")) {
        formattedConfigString = "\"$formattedConfigString"
    }
    if (!endsWith("\"")) {
        formattedConfigString = "$formattedConfigString\""
    }
    return formattedConfigString
}

fun String.unwrapQuotes(): String {
    var formattedConfigString: String = this
    if (formattedConfigString.startsWith("\"")) {
        formattedConfigString = if (formattedConfigString.length > 1) {
            formattedConfigString.substring(1)
        } else {
            ""
        }
    }
    if (formattedConfigString.endsWith("\"")) {
        formattedConfigString = if (formattedConfigString.length > 1) {
            formattedConfigString.substring(0, formattedConfigString.length - 1)
        } else {
            ""
        }
    }
    return formattedConfigString
}

/** Make the first instance of [boldText] in a CharSequece bold. */
fun CharSequence.makeBold(boldText: String): CharSequence {
    val start = indexOf(boldText)
    val end = start + boldText.length
    val span = StyleSpan(Typeface.BOLD)
    return if (this is Spannable) {
        setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this
    } else {
        SpannableString(this).apply {
            setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}


/** Compatibility removeIf since it was added in API 24 */
fun <T> MutableCollection<T>.compatRemoveIf(predicate: (T) -> Boolean): Boolean {
    val it = iterator()
    var removed = false
    while (it.hasNext()) {
        if (predicate(it.next())) {
            it.remove()
            removed = true
        }
    }
    return removed
}

