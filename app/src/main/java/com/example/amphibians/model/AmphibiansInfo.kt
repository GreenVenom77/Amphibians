package com.example.amphibians.model

import com.google.gson.annotations.SerializedName

data class AmphibiansInfo(
    val name: String,
    val type: String,
    val description: String,
    @SerializedName(value = "img_src")
    val imgSrc: String
)
