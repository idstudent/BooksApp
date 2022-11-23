package com.example.booksapp.view.util

import android.content.res.Resources
import android.util.TypedValue

inline val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()