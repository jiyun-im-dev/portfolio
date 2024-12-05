package com.jiyun.portfolio.domain.repository

import com.jiyun.portfolio.domain.constant.SkillType
import com.jiyun.portfolio.domain.entity.Project
import com.jiyun.portfolio.domain.entity.ProjectDetail
import com.jiyun.portfolio.domain.entity.ProjectSkill
import com.jiyun.portfolio.domain.entity.Skill
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectRepositoryTest(
    @Autowired val projectRepository: ProjectRepository,
    @Autowired val skillRepository: SkillRepository
) {

    val DATA_SIZE = 10

    private fun createProject(n: Int): Project {
        val project = Project(
            name = "${n}",
            description = "테스트 설명 ${n}",
            startYear = 2017,
            startMonth = 7,
            endYear = null,
            endMonth = null,
            isActive = true
        )

        // 프로젝트 디테일 추가
        val details = mutableListOf<ProjectDetail>()
        for (i in 1..n) {
            val detail = ProjectDetail(content = "테스트 디테일 ${n}", url = "https://${n}.com", isActive = true)
            details.add(detail)
        }
        project.addDetails(details)

        // 프로젝트 스킬 추가
        val skills = skillRepository.findAll() // 스킬 리포지토리에서 모든 스킬을 찾는다
        val skillsUsedInProject = skills.subList(0, n) // n개 만큼 잘라서 사용할 스킬의 리스트를 만든다
        for (skill in skillsUsedInProject) {
            val projectSkill = ProjectSkill(project = project, skill = skill)// 스킬을 이용해 프로젝트 스킬을 만듦
            project.skills.add(projectSkill) // 프로젝트의 스킬에 프로젝트 스킬 추가
        }

        return project
    }

    @BeforeAll
    fun beforeAll() {
        println("----- 스킬 데이터 초기화 시작 -----")
        val skills = mutableListOf<Skill>()
        for (i in 1..DATA_SIZE) {
            val skillTypes = SkillType.values()
            val skill = Skill(name = "테스트 ${i}", type = skillTypes[i % skillTypes.size].name, isActive = true)
            skills.add(skill)
        }
        skillRepository.saveAll(skills)
        println("----- 스킬 데이터 초기화 종료 -----")

        println("----- 프로젝트 데이터 초기화 시작 -----")
        val projects = mutableListOf<Project>()
        for (i in 1..DATA_SIZE) {
            val project = createProject(i)
            projects.add(project)
        }
        projectRepository.saveAll(projects)
        println("----- 프로젝트 데이터 초기화 종료 -----")
    }

    // 실제 테스트 메서드
    @Test // junit - 메서드를 테스트로 인식시킴
    fun testFindAll() {
        println("----- findAll 테스트 시작 -----")
        val projects = projectRepository.findAll()
        assertThat(projects).hasSize(DATA_SIZE)

        for (project in projects) {
            assertThat(project.details).hasSize(project.name.toInt())
            assertThat(project.skills).hasSize(project.name.toInt())
        }
        println("----- findAll 테스트 종료 -----")
    }

    // 실제 테스트 메서드
    @Test // junit - 메서드를 테스트로 인식시킴
    fun testFindAllByIsActive() {
        println("----- testFindAllByIsActive 테스트 시작 -----")
        val projects = projectRepository.findAllByIsActive(true)
        assertThat(projects).hasSize(DATA_SIZE)

        for (project in projects) {
            assertThat(project.details).hasSize(project.name.toInt())
            assertThat(project.skills).hasSize(project.name.toInt())
        }
        println("----- testFindAllByIsActive 테스트 종료 -----")
    }

}