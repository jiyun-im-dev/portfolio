package com.jiyun.portfolio.domain.repository

import com.jiyun.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long>