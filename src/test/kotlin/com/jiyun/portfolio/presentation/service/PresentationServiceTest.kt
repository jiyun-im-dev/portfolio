package com.jiyun.portfolio.presentation.service

import com.jiyun.portfolio.domain.entity.Introduction
import com.jiyun.portfolio.domain.entity.Link
import com.jiyun.portfolio.presentation.repository.PresentationRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PresentationServiceTest {

    // PresentationService 클래스 단위 테스트

    @InjectMocks  // 테스트할 객체에 목을 주입
    lateinit var presentationService: PresentationService

    @Mock
    lateinit var presentationRepository: PresentationRepository

    val DATA_SIZE = 9

    @Test
    fun testGetIntroductions() {
        //given
        val introductions = mutableListOf<Introduction>()
        for (i in 1..DATA_SIZE) {
            introductions.add(Introduction(content = "${i}", isActive = i % 2 == 0)) // 2, 4, 6, 8 = active
        }

        val activeIntroductions = introductions.filter { it.isActive } // 4

        Mockito.`when`(presentationRepository.getActiveIntroductions())
            .thenReturn(activeIntroductions)

        //when
        val introductionDtos = presentationService.getIntroductions()

        //then
        assertThat(introductionDtos).hasSize(DATA_SIZE / 2)
        for (introductionDto in introductionDtos) {
            assertThat(introductionDto.content.toInt()).isEven()
        }
    }

    @Test
    fun testGetLinks() {
        //given
        val links = mutableListOf<Link>()
        for (i in 1..DATA_SIZE) {
            links.add(Link(name = "${i}", content = "${i}", isActive = i % 2 != 0)) // 1, 3, 5, 7, 9 = active
        }

        val activeLinks = links.filter { it.isActive }

        Mockito.`when`(presentationRepository.getActiveLinks())
            .thenReturn(activeLinks) // 임의로 만든 active한 Link의 리스트를 반환

        //when
        val linkDtos = presentationService.getLinks()

        //then
        var expectedSize = DATA_SIZE / 2
        if (DATA_SIZE % 2 != 0) { // DATA_SIZE가 홀수일 경우
            expectedSize += 1
        }
        assertThat(linkDtos).hasSize(expectedSize)
        for (linkDto in linkDtos) { // active한 LinkDto들이 홀수인지 검사
            assertThat(linkDto.content.toInt()).isOdd()
        }
    }

}