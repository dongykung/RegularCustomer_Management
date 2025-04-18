package com.dkproject.regularcustomermanagement.domain.usecase

import com.dkproject.regularcustomermanagement.domain.model.Customer
import com.dkproject.regularcustomermanagement.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetAllCustomerUseCase(
    private val customerRepository: CustomerRepository
){
    operator fun invoke(): Flow<List<Customer>> = customerRepository.getAllCustomers()
}