package com.example.amphibians.ui

import com.example.amphibians.model.AmphibiansInfo

sealed interface AmphibiansUiState{
    data class Success(val info: List<AmphibiansInfo>): AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading: AmphibiansUiState
}
