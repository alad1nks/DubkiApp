package com.app.dubkiapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes

data class Bus(
    val id: Int? = null,
    val inTime: Long? = null,
    val inTimeMinutes: Int? = null,
    val inTimeHours: Int? = null,
    val dayTimeString: String? = null,
    @StyleRes val dayTimeStyleResId: Int? = null,
    @StringRes val inTimeStringResId: Int? = null,
    @StyleRes val inTimeStyleResId: Int? = null,
    @DrawableRes val inTimeDrawableResId: Int? = null,
    @StringRes val stationStringResId: Int? = null,
    @StyleRes val stationStyleResId: Int? = null
)
