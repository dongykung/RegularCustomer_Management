package com.dkproject.regularcustomermanagement.domain.model

import java.util.Date

data class Customer(
    val id: String, //고객 아이디
    val name: String, // 고객 이름
    val carNumber: String?, // 고객 차량 번호
    val affiliation: String?, // 고객 소속
    val visitedList: List<Date>, // 고객 방문 리스트
    val phoneNumber: String?, // 고객 휴대폰 번호
    val memoList: List<String>, // 고객 메모 리스트
    val isStar: Boolean, // 고객 즐겨찾기 여부
    val nickName: String?, // 고객 닉네임
    val createdAt: Date, // 고객 생성 날짜
    val tags: List<String> // 고객 태그 리스트
)