package com.dkproject.regularcustomermanagement.data.di

import com.dkproject.regularcustomermanagement.data.datasource.CustomerDataSource
import com.dkproject.regularcustomermanagement.data.datasource.CustomerDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindCustomerDataSource(
        customerDataSourceImpl: CustomerDataSourceImpl
    ): CustomerDataSource
}