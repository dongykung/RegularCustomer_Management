package com.dkproject.regularcustomermanagement.data.di

import com.dkproject.regularcustomermanagement.data.repository.CustomerRepositoryImpl
import com.dkproject.regularcustomermanagement.domain.repository.CustomerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCustomerRepository(
        customerRepositoryImpl: CustomerRepositoryImpl
    ): CustomerRepository
}