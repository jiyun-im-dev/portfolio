package com.jiyun.portfolio.domain.repository

import com.jiyun.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long>