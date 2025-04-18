package com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.AffiliationInfo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AffiliationViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(AffiliationUiState())
    val uiState = _uiState.asStateFlow()

    fun updateAffiliationName(name: String) {
        _uiState.value = _uiState.value.copy(affiliationName = name)
    }

    fun addTag(tag: String) {
        _uiState.value = _uiState.value.copy(tags = _uiState.value.tags + tag)
    }

    fun removeTag(tag: String) {
        _uiState.value = _uiState.value.copy(tags = _uiState.value.tags - tag)
    }
}

data class AffiliationUiState(
    val affiliationName: String = "",
    val tags: List<String> = emptyList()
)