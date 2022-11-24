package com.example.booksapp.view.util

import android.content.res.Resources
import android.util.TypedValue
import android.os.SystemClock
import android.view.View

inline val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()

val DECIMAL_FORMAT = java.text.DecimalFormat("###,###")

inline val Int.numberFormat: String
    get() = DECIMAL_FORMAT.format(this)

inline val String.won: String
    get() = "${this}원"

fun View.setOnSingleClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SingleClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

class SingleClickListener(
    private var defaultInterval: Int = 500,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }

        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}