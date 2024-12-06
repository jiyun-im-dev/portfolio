package com.jiyun.portfolio.presentation.service

import com.jiyun.portfolio.presentation.dto.IntroductionDto
import com.jiyun.portfolio.presentation.dto.LinkDto
import com.jiyun.portfolio.presentation.dto.ProjectDto
import com.jiyun.portfolio.presentation.dto.ResumeDto
import com.jiyun.portfolio.presentation.repository.PresentationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository
) {

    @Transactional(readOnly = true)
    fun getIntroductions(): List<IntroductionDto> {
        return presentationRepository.getActiveIntroductions().map { IntroductionDto(it) }
    }

    @Transactional(readOnly = true)
    fun getLinks(): List<LinkDto> {
        return presentationRepository.getActiveLinks().map { LinkDto(it) }
    }

    @Transactional(readOnly = true)
    fun getResume(): ResumeDto {
        val experiences = presentationRepository.getActiveExperiences()
        val achievements = presentationRepository.getActiveAchievements()
        val skills = presentationRepository.getActiveSkills()

        return ResumeDto(experiences, achievements, skills)
    }

    @Transactional(readOnly = true)
    fun getProjects(): List<ProjectDto> {
        return presentationRepository.getActiveProjects().map { ProjectDto(it) }
    }

}