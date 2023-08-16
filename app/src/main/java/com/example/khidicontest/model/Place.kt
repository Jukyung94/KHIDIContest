package com.example.khidicontest.model

import androidx.annotation.DrawableRes

data class Place(
    @DrawableRes val imageResourceId: Int,
    val title: String,
    val address: String,
    val tel: String,
)
