package com.jiyun.portfolio.domain.entity

import jakarta.persistence.MappedSuperclass

@MappedSuperclass // 이 클래스를 상속받는 엔티티 클래스가 이 클래스의 필드들을 해당하는 테이블의 컬럼과 매핑할 수 있게 해줌
abstract class BaseEntity