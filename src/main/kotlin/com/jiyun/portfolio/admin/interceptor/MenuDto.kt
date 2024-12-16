package com.jiyun.portfolio.admin.interceptor

data class MenuDto(
    // 대메뉴 - 메뉴명, 포함된 소메뉴(PageDto)
    val name: String,
    val pages: List<PageDto>
)