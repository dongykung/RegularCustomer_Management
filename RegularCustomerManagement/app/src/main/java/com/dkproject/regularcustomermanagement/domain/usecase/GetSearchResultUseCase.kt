package com.dkproject.regularcustomermanagement.domain.usecase

import com.dkproject.regularcustomermanagement.domain.repository.CustomerRepository

class GetSearchResultUseCase(
    private val customerRepository: CustomerRepository
) {
    operator fun invoke(query: String) = customerRepository.getSearchResults(query)
}