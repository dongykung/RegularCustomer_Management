package com.dkproject.regularcustomermanagement.data.repository

import com.dkproject.regularcustomermanagement.data.datasource.CustomerDataSource
import com.dkproject.regularcustomermanagement.data.model.toEntity
import com.dkproject.regularcustomermanagement.domain.model.Customer
import com.dkproject.regularcustomermanagement.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val customerDataSource: CustomerDataSource
): CustomerRepository{
    override fun getAllCustomers(): Flow<List<Customer>> {
        return customerDataSource.getAllCustomers().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getSearchResults(query: String): Flow<List<Customer>> {
        return customerDataSource.getSearchResults(query).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun addCustomer(customer: Customer): Result<Unit> = runCatching {
        customerDataSource.addCustomer(customer.toEntity())
    }

    override suspend fun updateCustomer(customer: Customer): Result<Unit> = runCatching {
        customerDataSource.updateCustomer(customer.toEntity())
    }
    override suspend fun deleteCustomer(customer: Customer): Result<Unit> = runCatching {
        customerDataSource.deleteCustomer(customer.toEntity())
    }


}