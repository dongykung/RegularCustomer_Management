package com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.Memo

import android.text.BoringLayout
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(MemoUiState())
    val uiState = _uiState.asStateFlow()

    fun updateIsStar(isStar: Boolean) {
        _uiState.value = _uiState.value.copy(isStar = isStar)
    }

    fun pluseMemo(memo: String) {
        _uiState.value = _uiState.value.copy(memoList = _uiState.value.memoList + memo)
    }

    fun removeMemo(memo: String) {
        _uiState.value = _uiState.value.copy(memoList = _uiState.value.memoList - memo)
    }
}

data class MemoUiState(
    val isStar: Boolean = false,
    val memoList: List<String> = emptyList()
)