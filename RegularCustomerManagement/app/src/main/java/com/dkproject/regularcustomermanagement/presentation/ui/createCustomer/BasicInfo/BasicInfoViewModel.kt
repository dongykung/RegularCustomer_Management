package com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.BasicInfo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BasicInfoViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(BasicInfoUiState())
    val uiState = _uiState.asStateFlow()

    fun updateUserName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateNickname(nickname: String) {
        _uiState.value = _uiState.value.copy(nickName = nickname)
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _uiState.value = _uiState.value.copy(phoneNumber = phoneNumber)
    }

    fun updateCarNumber(carNumber: String) {
        _uiState.value = _uiState.value.copy(carNumber = carNumber)
    }
}

data class BasicInfoUiState(
    val name: String = "",
    val phoneNumber: String? = null,
    val nickName: String? = null,
    val carNumber: String? = null
)