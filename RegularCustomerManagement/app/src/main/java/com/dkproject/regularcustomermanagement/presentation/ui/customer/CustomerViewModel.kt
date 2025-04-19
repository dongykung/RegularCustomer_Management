package com.dkproject.regularcustomermanagement.presentation.ui.customer

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.domain.model.Customer
import com.dkproject.regularcustomermanagement.domain.usecase.GetAllCustomerUseCase
import com.dkproject.regularcustomermanagement.domain.usecase.GetSearchResultUseCase
import com.dkproject.regularcustomermanagement.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val getAllCustomerUseCase: GetAllCustomerUseCase,
    private val getSearchResultUseCase: GetSearchResultUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchResults: StateFlow<List<Customer>> = _searchQuery
        .debounce(300L)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if(query.isBlank()) flowOf(emptyList())
            else getSearchResultUseCase(query)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _uiState = MutableStateFlow<UiState<ImmutableList<Customer>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getCustomers()
    }

    fun getCustomers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getAllCustomerUseCase()
                .flowOn(context = Dispatchers.IO)
                .catch {
                    _uiState.value = UiState.Error(message = context.getString(R.string.failgetcustomer))
                }
                .collect { list ->
                    Log.d("CustomerViewModel", "getCustomers: $list")
                    _uiState.value = UiState.Success(list.toImmutableList())
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}

