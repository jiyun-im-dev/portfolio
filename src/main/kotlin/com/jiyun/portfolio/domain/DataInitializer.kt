package com.jiyun.portfolio.domain

import com.jiyun.portfolio.domain.constant.SkillType
import com.jiyun.portfolio.domain.entity.*
import com.jiyun.portfolio.domain.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitializer( // 생성자 주입?
    private val achievementRepository: AchievementRepository,
    private val experienceRepository: ExperienceRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository
) {

    @PostConstruct
    fun initializeData() {
        // 성과
        val achievements = mutableListOf<Achievement>(
            // 생성자로 바로 객체 생성
            Achievement(
                title = "2024 케이크 많이 먹기 대회 우승",
                description = "초코케익을 특히 잘 먹었다고 함",
                achievedDate = LocalDate.of(2024, 12, 13),
                host = "전국 제과 협회",
                isActive = true
            ), Achievement(
                title = "라옹이 예뻐하기",
                description = "놀아주는 능력이 출중함",
                achievedDate = LocalDate.of(2024, 12, 1),
                host = "우리집",
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        // 설명
        val introductions = mutableListOf<Introduction>(
            Introduction(content = "짱짱걸", isActive = true),
            Introduction(content = "빨리 이해하고 배우는 짱짱걸이에요", isActive = true)
        )
        introductionRepository.saveAll(introductions)

        // 링크
        val links = mutableListOf<Link>(
            Link(name = "github", content = "https://github.com/jiyun-im-dev", isActive = true),
            Link(name = "linkedin", content = "https://www.linkedin.com/in/ji-yun-im-5635832a2/", isActive = true),
        )
        linkRepository.saveAll(links)

        // 경험
        val experience1 = Experience(
            title = "고양이 밥 주기",
            description = "라옹이에게 밥을 주는 일",
            startYear = 2017,
            startMonth = 7,
            endYear = null,
            endMonth = null,
            isActive = true,
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "라옹이에게 적절한 양을 맞춤", isActive = true),
                ExperienceDetail(content = "로얄캐닌 사용", isActive = true)
            )
        )

        val experience2 = Experience(
            title = "성신여자대학교",
            description = "컴퓨터공학과",
            startYear = 2020,
            startMonth = 9,
            endYear = 2023,
            endMonth = 2,
            isActive = true,
        )
        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(content = "머시기머시기를 배움", isActive = true)
            )
        )
        val experience3 = Experience(
            title = "네오뉴트라 근무",
            description = "정보시스템팀",
            startYear = 2023,
            startMonth = 7,
            endYear = 2024,
            endMonth = 8,
            isActive = true,
        )
        experience3.addDetails(
            mutableListOf(
                ExperienceDetail(content = "MariaDB, PostgreSQL 사용", isActive = true),
                ExperienceDetail(content = "자바스크립트 사용", isActive = true),
                ExperienceDetail(content = "그룹웨어와 연동 로직 구현", isActive = true),
            )
        )
        experienceRepository.saveAll(mutableListOf(experience1, experience2, experience3))

        // 스킬
        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.FRAMEWORK.name, isActive = true)
        val mysql = Skill(name = "MySQL", type = SkillType.DATABASE.name, isActive = true)
        val postgresql = Skill(name = "PostgreSQL", type = SkillType.DATABASE.name, isActive = true)
        val git = Skill(name = "Git", type = SkillType.TOOL.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java, spring, mysql, postgresql, git))

        // 프로젝트
        val project1 = Project(
            name = "라옹이를 쓰다듬어라!",
            description = "하지만 화내지 않을 때까지만 쓰다듬어야 한다.",
            startYear = 2017,
            startMonth = 7,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(
                    content = "물릴 수도 있는 위험한 프로젝트",
                    url = "https://petraon.com",
                    isActive = true
                ),
                ProjectDetail(
                    content = "라옹이의 엉덩이를 적당히 토닥거려야 한다",
                    url = null,
                    isActive = true
                )
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = mysql),
                ProjectSkill(project = project1, skill = git)
            )
        )
        val project2 = Project(
            name = "케이크를 먹어라!",
            description = "하지만 적당히 먹어야 한다.",
            startYear = 2024,
            startMonth = 10,
            endYear = 2024,
            endMonth = 12,
            isActive = true
        )
        project2.addDetails(
            mutableListOf(
                ProjectDetail(content = "냠냠 맛있게 먹는 게 중요해", url = "https://sungsimdang.co.kr/", isActive = true),
                ProjectDetail(content = "초코 케익이 제일 좋아", url = null, isActive = true)
            )
        )
        project2.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project2, skill = java),
                ProjectSkill(project = project2, skill = postgresql)
            )
        )
        projectRepository.saveAll(mutableListOf(project1, project2))
    }

}