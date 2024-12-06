package com.jiyun.portfolio.presentation.dto

data class ExperienceDto(
    val title: String,
    val description: String,
    val startYearMonth: String,
    val endYearMonth: String?,
    val details: List<String>
)