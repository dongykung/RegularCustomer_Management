package com.dkproject.regularcustomermanagement.data.di

import com.dkproject.regularcustomermanagement.domain.repository.CustomerRepository
import com.dkproject.regularcustomermanagement.domain.usecase.AddCustomerUseCase
import com.dkproject.regularcustomermanagement.domain.usecase.GetAllCustomerUseCase
import com.dkproject.regularcustomermanagement.domain.usecase.GetSearchResultUseCase
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

    @Provides
    @Singleton
    fun provideGetAllCustomerUseCase(
        customerRepository: CustomerRepository
    ): GetAllCustomerUseCase {
        return GetAllCustomerUseCase(customerRepository)
    }

    @Provides
    @Singleton
    fun provideGetSearchResultUseCase(
        customerRepository: CustomerRepository
    ): GetSearchResultUseCase {
        return GetSearchResultUseCase(customerRepository)
    }
}