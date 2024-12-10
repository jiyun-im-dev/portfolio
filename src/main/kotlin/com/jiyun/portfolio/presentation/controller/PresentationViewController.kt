package com.jiyun.portfolio.presentation.controller

import com.jiyun.portfolio.domain.constant.SkillType
import com.jiyun.portfolio.presentation.service.PresentationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PresentationViewController( // 서버 사이드 렌더링
    private val presentationService: PresentationService
) {

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }

    @GetMapping("/") // 인덱스 페이지에는 설명, 링크가 나옴
    fun index(model: Model): String {
        model.addAttribute("introductions", presentationService.getIntroductions())
        model.addAttribute("links", presentationService.getLinks())
        return "presentation/index"
    }

    @GetMapping("/resume")
    fun resume(model: Model): String {
        model.addAttribute("resume", presentationService.getResume())
        model.addAttribute("skillTypes", SkillType.values())
        return "presentation/resume"
    }

    @GetMapping("/projects")
    fun projects(model: Model): String {
        model.addAttribute("projects", presentationService.getProjects())
        return "presentation/projects"
    }

}