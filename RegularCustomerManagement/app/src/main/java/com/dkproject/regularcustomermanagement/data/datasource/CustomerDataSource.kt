package com.dkproject.regularcustomermanagement.data.datasource

import com.dkproject.regularcustomermanagement.data.dao.CustomerDao
import com.dkproject.regularcustomermanagement.data.model.CustomerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CustomerDataSource {
    fun getAllCustomers(): Flow<List<CustomerEntity>>
    fun getSearchResults(query: String): Flow<List<CustomerEntity>>
    suspend fun addCustomer(customerEntity: CustomerEntity)
    suspend fun updateCustomer(customerEntity: CustomerEntity)
    suspend fun deleteCustomer(customerEntity: CustomerEntity)
}

class CustomerDataSourceImpl @Inject constructor(
    private val customerDao: CustomerDao
): CustomerDataSource {
    override fun getAllCustomers(): Flow<List<CustomerEntity>> {
        return customerDao.getAllCustomers()
    }

    override fun getSearchResults(query: String): Flow<List<CustomerEntity>> {
        return customerDao.getSearchResults(query = query)
    }

    override suspend fun addCustomer(customerEntity: CustomerEntity) {
        customerDao.insertCustomer(customerEntity)
    }

    override suspend fun updateCustomer(customerEntity: CustomerEntity) {
        customerDao.updateCustomer(customerEntity)
    }

    override suspend fun deleteCustomer(customerEntity: CustomerEntity) {
        customerDao.deleteCustomer(customerEntity)
    }

}