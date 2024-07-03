package com.example.amphibians.network

import com.example.amphibians.model.AmphibiansInfo
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getInfo(): List<AmphibiansInfo>
}