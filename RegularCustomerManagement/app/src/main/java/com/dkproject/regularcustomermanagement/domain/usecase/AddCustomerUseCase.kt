package com.dkproject.regularcustomermanagement.domain.usecase

import com.dkproject.regularcustomermanagement.domain.model.Customer
import com.dkproject.regularcustomermanagement.domain.repository.CustomerRepository

class AddCustomerUseCase(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(customer: Customer): Result<Unit> = customerRepository.addCustomer(customer)
}