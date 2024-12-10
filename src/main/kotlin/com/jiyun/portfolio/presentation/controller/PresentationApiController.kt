package com.jiyun.portfolio.presentation.controller

import com.jiyun.portfolio.presentation.dto.IntroductionDto
import com.jiyun.portfolio.presentation.dto.LinkDto
import com.jiyun.portfolio.presentation.dto.ProjectDto
import com.jiyun.portfolio.presentation.dto.ResumeDto
import com.jiyun.portfolio.presentation.service.PresentationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PresentationApiController( // 백->프론트로 데이터 전달 후 클라이언트 사이드 렌더링
    private val presentationService: PresentationService
) {

    @GetMapping("/test")
    fun test(): String {
        return "ok"
    }

    @GetMapping("/v1/introductions") // api 컨트롤러엔 보통 버전을 붙인다
    fun getIntroductions(): List<IntroductionDto> {
        return presentationService.getIntroductions()
    }

    @GetMapping("/v1/links")
    fun getLinks(): List<LinkDto> {
        return presentationService.getLinks()
    }

    @GetMapping("/v1/resume") // api 컨트롤러엔 보통 버전을 붙인다
    fun getResume(): ResumeDto {
        return presentationService.getResume()
    }

    @GetMapping("/v1/projects")
    fun getProjects(): List<ProjectDto> {
        return presentationService.getProjects()
    }

}