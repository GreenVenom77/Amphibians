package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val amphibiansInfoRepository: AmphibiansInfoRepository
}

class DefaultAppContainer(): AppContainer {
    private val baseUrl: String = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    override val amphibiansInfoRepository: AmphibiansInfoRepository by lazy {
        NetworkAmphibiansInfoRepository(retrofitService)
    }
}