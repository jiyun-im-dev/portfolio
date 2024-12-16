package com.jiyun.portfolio.admin.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class AdminInterceptor : HandlerInterceptor {

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        val menus = listOf<MenuDto>(
            // MenuDto: 대메뉴
            MenuDto(
                name = "Index", pages = listOf<PageDto>(
                    // PageDto: 소메뉴
                    PageDto(name = "Introduction", url = "/admin/introduction"),
                    PageDto(name = "Link", url = "/admin/link")
                )
            ),
            MenuDto(
                name = "Resume", pages = listOf<PageDto>(
                    PageDto(name = "Experience", url = "/admin/experience"),
                    PageDto(name = "Achievement", url = "/admin/achievements"),
                    PageDto(name = "Skill", url = "/admin/skills")
                )
            ),
            MenuDto(
                name = "Projects", pages = listOf<PageDto>(
                    PageDto(name = "Project", url = "/admin/project"),
                    PageDto(name = "ProjectSkill", url = "/admin/project/skill")
                )
            )
        )
        modelAndView?.model?.put("menus", menus)
    }

}