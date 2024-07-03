package com.example.amphibians.data

import com.example.amphibians.model.AmphibiansInfo
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansInfoRepository {
    suspend fun getAmphibiansInfo(): List<AmphibiansInfo>
}

class NetworkAmphibiansInfoRepository(
    private val amphibiansApi: AmphibiansApiService
): AmphibiansInfoRepository {
    override suspend fun getAmphibiansInfo(): List<AmphibiansInfo> = amphibiansApi.getInfo()
}