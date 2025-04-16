package com.dkproject.regularcustomermanagement.presentation.ui.createCustomer

import androidx.lifecycle.ViewModel
import com.dkproject.regularcustomermanagement.domain.model.Customer
import com.dkproject.regularcustomermanagement.presentation.model.BasicInfo
import com.dkproject.regularcustomermanagement.presentation.model.CreateCustomerStep
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateCustomerViewModel @Inject constructor(

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
}

data class CreateCustomerUiState(
    val currentStep: CreateCustomerStep = CreateCustomerStep.BASIC,
    val customer: Customer = Customer()
)