package com.dkproject.regularcustomermanagement.presentation.model

import androidx.annotation.StringRes

sealed class BaseUiEvent {
    data class ShowToast(@StringRes val message: Int) : BaseUiEvent()
    data object PopBackStack : BaseUiEvent()
}