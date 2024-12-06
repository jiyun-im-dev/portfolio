package com.jiyun.portfolio.presentation.dto

import com.jiyun.portfolio.domain.entity.Introduction

data class IntroductionDto(
    val content: String
) {

    constructor(introduction: Introduction) : this(
        content = introduction.content
    )

}