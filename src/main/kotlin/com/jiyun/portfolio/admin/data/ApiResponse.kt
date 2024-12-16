package com.jiyun.portfolio.admin.data

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponse<T>(status: HttpStatus) : ResponseEntity<T>(status) {

    // 여러 컨트롤러에서 동일한 응답을 편하게 사용하기 위해 static 메서드로 응답 객체 생성
    companion object {
        fun successCreate(): ResponseEntity<Any> {
            return ResponseEntity.ok("데이터가 저장되었습니다.")
        }

        fun successUpdate(): ResponseEntity<Any> {
            return ResponseEntity.ok("데이터가 수정되었습니다.")
        }

        fun successDelete(): ResponseEntity<Any> {
            return ResponseEntity.ok("데이터가 삭제되었습니다.")
        }
    }

}