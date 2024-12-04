package com.jiyun.portfolio.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity // JPA에서 이 클래스는 테이블과 매핑되는 엔티티 클래스임을 표시한다
class Achievement( // 기본 생성자 정의
    title: String,
    description: String,
    achievedDate: LocalDate?,
    host: String,
    isActive: Boolean
) : BaseEntity() { // BaseEntity를 상속

    @Id // 이 필드가 PK임을 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 전략 - 데이터베이스(MySQL의 Auto Increment)에 위임
    @Column(name = "achievement_id") // 이름을 생략해도 필드는 캐멀 케이스, DB는 스네이크 케이스로 된 경우 자동으로 매핑할 컬럼을 찾아준다
    var id: Long? = null // 엔티티 생성 시가 아니라 엔티티를 데이터베이스에 저장할 때 DB가 ID를 생성하므로 널을 허용한다

    var title: String = title // 생성자에서 받은 title을 초기값으로 넣어준다

    var description: String = description

    var achievedDate: LocalDate? = achievedDate

    var host: String = host

    // DB에서는 스네이크 케이스로 변환되어 is_active로 들어감
    var isActive: Boolean = isActive

}