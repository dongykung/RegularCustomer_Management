package com.dkproject.regularcustomermanagement.presentation.ui.createCustomer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkproject.regularcustomermanagement.domain.model.Customer
import com.dkproject.regularcustomermanagement.domain.usecase.AddCustomerUseCase
import com.dkproject.regularcustomermanagement.presentation.model.BasicInfo
import com.dkproject.regularcustomermanagement.presentation.model.CreateCustomerStep
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCustomerViewModel @Inject constructor(
    private val addCustomerUseCase: AddCustomerUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateCustomerUiState())
    val uiState = _uiState.asStateFlow()

    fun updateCurrentStep(step: CreateCustomerStep) {
        _uiState.update { it.copy(currentStep = step) }
    }

    fun updateBasicInfo(basicInfo: BasicInfo) {
        _uiState.update {
            it.copy(
                customer = it.customer.copy(
                    name = basicInfo.name,
                    phoneNumber = basicInfo.phoneNumber,
                    nickName = basicInfo.nickName,
                    carNumber = basicInfo.carNumber
                )
            )
        }
    }

    fun updateAffiliationAndTags(affiliationName: String, tags: List<String>) {
        _uiState.update {
            it.copy(
                customer = it.customer.copy(
                    affiliation = affiliationName,
                    tags = tags
                )
            )
        }
    }

    fun updateStarAndMemo(isStar: Boolean, memo: List<String>) {
        _uiState.update {
            it.copy(
                customer = it.customer.copy(
                    isStar = isStar,
                    memoList = memo
                )
            )
        }
    }

    fun addCustomer() {
        viewModelScope.launch(context = Dispatchers.IO) {
            addCustomerUseCase(customer = uiState.value.customer)
                .onFailure {
                    Log.d("dk", "fail")
                }
                .onSuccess {
                    Log.d("dk", "success")
                }
        }
    }
}

data class CreateCustomerUiState(
    val currentStep: CreateCustomerStep = CreateCustomerStep.BASIC,
    val customer: Customer = Customer()
)