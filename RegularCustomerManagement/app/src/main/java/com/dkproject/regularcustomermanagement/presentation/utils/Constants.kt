package com.dkproject.regularcustomermanagement.presentation.utils

import com.dkproject.regularcustomermanagement.domain.model.Customer
import com.dkproject.regularcustomermanagement.presentation.model.CreateCustomerStep

object Constants {
    val CREATECUSTOMERSTEPS = listOf(
        CreateCustomerStep.BASIC,
        CreateCustomerStep.AFFILIATION,
        CreateCustomerStep.MEMO
    )
    val MAX_CUSTOMER_NAME_LENGTH = 15

    val CUSTOMER_MOCKS = listOf(
        Customer(id = "1", name = "김동규", affiliation = "회사", carNumber = "82호 1352"),
        Customer(id = "2", name = "김태풍",affiliation = "농구", phoneNumber = "01015231632"),
        Customer(id = "3",name = "김동경",affiliation = "농구"),
        Customer(id = "4",name = "변준영",affiliation = "농구", phoneNumber = "01085239533", carNumber = "63호 2421"),
        Customer(id = "5",name = "김태민", affiliation = "회사", phoneNumber = "01012341234", carNumber = "15너 2523", tags = listOf("단골", "태풍"))
    )

    fun formatPhoneNumber(phone: String): String {
        val regex = Regex("""\d{3}-\d{3,4}-\d{4}""")
        return if (regex.matches(phone)) {
            phone // 이미 포맷된 상태
        } else {
            val digits = phone.filter { it.isDigit() }
            if (digits.length == 11) {
                "${digits.substring(0, 3)}-${digits.substring(3, 7)}-${digits.substring(7)}"
            } else if (digits.length == 10) {
                "${digits.substring(0, 3)}-${digits.substring(3, 6)}-${digits.substring(6)}"
            } else {
                phone // 유효하지 않으면 원본 반환
            }
        }
    }
}