package com.jiyun.portfolio.presentation.dto

import com.jiyun.portfolio.domain.entity.ProjectDetail

data class ProjectDetailDto(
    val content: String,
    val url: String?
) {

    constructor(projectDetail: ProjectDetail) : this(
        content = projectDetail.content,
        url = projectDetail.url
    )

}