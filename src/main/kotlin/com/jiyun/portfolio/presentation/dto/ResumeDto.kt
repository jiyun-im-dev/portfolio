package com.jiyun.portfolio.presentation.dto

import com.jiyun.portfolio.domain.entity.Achievement
import com.jiyun.portfolio.domain.entity.Experience
import com.jiyun.portfolio.domain.entity.Skill
import java.time.format.DateTimeFormatter

class ResumeDto(
    experiences: List<Experience>,
    achievements: List<Achievement>,
    skills: List<Skill>
) {

    var experiences: List<ExperienceDto> = experiences.map {
        ExperienceDto(
            title = it.title,
            description = it.description,
            startYearMonth = "${it.startYear}.${it.startMonth}",
            endYearMonth = it.getEndYearMonth(),
            details = it.details.filter { it.isActive }.map { it.content }
        )
    }

    var achievements: List<AchievementDto> = achievements.map {
        AchievementDto(
            title = it.title,
            description = it.description,
            host = it.host,
            achievedDate = it.achievedDate
                ?.format(DateTimeFormatter.ISO_LOCAL_DATE) // yyyy-mm-dd
                ?.replace("-", ".")        // yyyy.mm.dd
        )
    }

    var skills: List<SkillDto> = skills.map { SkillDto(it) }

}