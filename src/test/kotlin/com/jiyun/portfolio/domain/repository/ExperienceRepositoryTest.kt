package com.jiyun.portfolio.domain.repository

import com.jiyun.portfolio.domain.entity.Experience
import com.jiyun.portfolio.domain.entity.ExperienceDetail
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExperienceRepositoryTest(
    @Autowired val experienceRepository: ExperienceRepository
) {

    val DATA_SIZE = 10

    // 테스트 데이터 초기화할 때 더미 엔티티 생성
    private fun createExperience(n: Int): Experience {
        val experience = Experience(
            title = "${n}",
            description = "테스트 설명 ${n}",
            startYear = 2024,
            startMonth = 8,
            endYear = 2024,
            endMonth = 12,
            isActive = true
        )
        val details = mutableListOf<ExperienceDetail>()
        for (i in 1..n) {
            val experienceDetail = ExperienceDetail(content = "테스트 ${i} 디테일", isActive = true)
            details.add(experienceDetail)
        }
        experience.addDetails(details)

        return experience
    }

    // 실제로 테스트 데이터 초기화하는 메서드
    @BeforeAll // junit
    fun beforeAll() {
        println("----- 테스트 데이터 초기화 이전 조회 시작 -----")
        val beforeInitialization = experienceRepository.findAll()
        assertThat(beforeInitialization).hasSize(0)
        println("----- 테스트 데이터 초기화 이전 조회 종료 -----")

        println("----- 테스트 데이터 초기화 시작 -----")
        val experiences = mutableListOf<Experience>()
        for (i in 1..DATA_SIZE){
            val experience = createExperience(i)
            experiences.add(experience)
        }
        experienceRepository.saveAll(experiences)
        println("----- 테스트 데이터 초기화 종료 -----")
    }

    // 실제 테스트 메서드
    @Test // junit - 메서드를 테스트로 인식시킴
    fun testFindAll() {
        println("----- findAll 테스트 시작 -----")
        val experiences = experienceRepository.findAll()
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
        }
        println("----- findAll 테스트 종료 -----")
    }

    // 실제 테스트 메서드
    @Test // junit - 메서드를 테스트로 인식시킴
    fun testFindAllByIsActive() {
        println("----- testFindAllByIsActive 테스트 시작 -----")
        val experiences = experienceRepository.findAllByIsActive(true)
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
        }
        println("----- testFindAllByIsActive 테스트 종료 -----")
    }

}