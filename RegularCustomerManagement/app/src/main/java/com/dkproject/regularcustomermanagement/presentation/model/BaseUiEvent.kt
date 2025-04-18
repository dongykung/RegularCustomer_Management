package com.dkproject.regularcustomermanagement.presentation.model

sealed class BaseUiEvent {
    data class ShowToast(val message: String) : BaseUiEvent()
    data object PopBackStack : BaseUiEvent()
}