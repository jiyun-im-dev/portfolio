package com.jiyun.portfolio.presentation.dto

import com.jiyun.portfolio.domain.entity.Skill

data class SkillDto(
    val name: String,
    val type: String
) {

    constructor(skill: Skill) : this(
        name = skill.name,
        type = skill.type.name
    )

}