package com.dkproject.regularcustomermanagement.domain.repository

import com.dkproject.regularcustomermanagement.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getAllCustomers(): Flow<List<Customer>>
    suspend fun addCustomer(customer: Customer): Result<Unit>
    suspend fun updateCustomer(customer: Customer): Result<Unit>
    suspend fun deleteCustomer(customer: Customer): Result<Unit>
}