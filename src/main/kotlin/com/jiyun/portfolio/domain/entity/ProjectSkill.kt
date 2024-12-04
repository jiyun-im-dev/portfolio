package com.jiyun.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class ProjectSkill(project: Project, skill: Skill) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_skill_id")
    var id: Long? = null

    @ManyToOne(targetEntity = Project::class, fetch = FetchType.LAZY) // 프로젝트 당 스킬이 여러 개
    @JoinColumn(name = "project_id", nullable = false)                // 연관관계 주인은 ProjectSkill 엔티티
    var project: Project = project

    @ManyToOne(targetEntity = Skill::class, fetch = FetchType.LAZY) // 프로젝트 스킬이 여러 개
    @JoinColumn(name = "skill_id", nullable = false)                // 연관관계 주인은 ProjectSkill 엔티티
    var skill: Skill = skill

}