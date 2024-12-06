package com.jiyun.portfolio.presentation.dto

import com.jiyun.portfolio.domain.entity.Project

data class ProjectDto(
    val name: String,
    val description: String,
    val startYearMonth: String,
    val endYearMonth: String?,
    val details: List<ProjectDetailDto>,
    val skills: List<SkillDto>?
) {

    constructor(project: Project) : this(
        name = project.name,
        description = project.description,
        startYearMonth = "${project.startYear}.${project.startMonth}",
        endYearMonth = project.getEndYearMonth(),
        details = project.details.filter { it.isActive }.map { ProjectDetailDto(it) },
        skills = project.skills.map { it.skill }.filter { it.isActive }.map { SkillDto(it) }
    )

}