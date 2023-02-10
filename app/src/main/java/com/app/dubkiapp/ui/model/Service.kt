package com.app.dubkiapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Service(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val onClick: () -> Unit = {
    }
)