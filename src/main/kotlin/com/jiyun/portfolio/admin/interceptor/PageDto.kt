package com.jiyun.portfolio.admin.interceptor

// DTO가 왜 이런 패키지에 들어가지...
data class PageDto(
    // 소메뉴 - 메뉴명, 해당 페이지로 이동하는 경로
    val name: String,
    val url: String
)