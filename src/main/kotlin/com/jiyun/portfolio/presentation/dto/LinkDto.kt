package com.jiyun.portfolio.presentation.dto

import com.jiyun.portfolio.domain.entity.Link

data class LinkDto(
    val name: String,
    val content: String
) {

    constructor(link: Link) : this(
        name = link.name.lowercase(),
        content = link.content
    )

}