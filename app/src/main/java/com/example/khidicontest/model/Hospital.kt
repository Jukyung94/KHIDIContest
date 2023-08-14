package com.example.khidicontest.model

import androidx.annotation.DrawableRes

data class Hospital (
    @DrawableRes val imageResourceId: Int,
    val name: String,
    val type: String,
    val location: String,
    val address: String,
    val countries: String,
)