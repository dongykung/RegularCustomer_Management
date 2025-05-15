package com.dkproject.regularcustomermanagement.domain.usecase

import com.dkproject.regularcustomermanagement.domain.model.Customer
import com.dkproject.regularcustomermanagement.domain.repository.CustomerRepository

class UpdateCustomerUseCase (
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(customer: Customer): Result<Unit> {
        return customerRepository.updateCustomer(customer)
    }
}