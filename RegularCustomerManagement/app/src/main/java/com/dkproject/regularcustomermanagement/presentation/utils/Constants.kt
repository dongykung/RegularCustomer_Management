package com.dkproject.regularcustomermanagement.presentation.utils

import com.dkproject.regularcustomermanagement.presentation.model.CreateCustomerStep

object Constants {
    val CREATECUSTOMERSTEPS = listOf(
        CreateCustomerStep.BASIC,
        CreateCustomerStep.AFFILIATION,
        CreateCustomerStep.MEMO
    )
    val MAX_CUSTOMER_NAME_LENGTH = 15
}