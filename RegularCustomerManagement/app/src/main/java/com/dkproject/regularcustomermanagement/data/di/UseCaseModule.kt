package com.dkproject.regularcustomermanagement.data.di

import com.dkproject.regularcustomermanagement.domain.repository.CustomerRepository
import com.dkproject.regularcustomermanagement.domain.usecase.AddCustomerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideAddCustomerUseCase(
        customerRepository: CustomerRepository
    ): AddCustomerUseCase {
        return AddCustomerUseCase(customerRepository)
    }
}