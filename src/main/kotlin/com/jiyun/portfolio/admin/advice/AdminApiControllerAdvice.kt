package com.jiyun.portfolio.admin.advice

import com.jiyun.portfolio.admin.exception.AdminException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// 컨트롤러에 공통적으로 적용되는 ExceptionHandler
@RestControllerAdvice
class AdminApiControllerAdvice {

    val log = LoggerFactory.getLogger(AdminApiControllerAdvice::class.java)

    // 개발자가 정의하여 던지는 예외 처리
    @ExceptionHandler
    fun handleException(e: AdminException): ResponseEntity<String> {
        log.info(e.message, e)

        return ResponseEntity.status(e.httpStatus).body(e.message)
    }

    // 스프링(?) Validation에서 던지는 MethodArgumentNotValidException 처리
    @ExceptionHandler
    fun handleException(e: MethodArgumentNotValidException): ResponseEntity<String>  {
        log.info(e.message, e)

        val fieldError = e.bindingResult.fieldErrors[0]
        val message = "[${fieldError.field}] ${fieldError.defaultMessage}"

        return ResponseEntity.badRequest().body(message) // 클라이언트에서 보낸 데이터의 오류이기 때문에 BadRequest
    }

    // 그 외 예상하지 못한 모든 예외 처리
    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<String> {
        log.info(e.message, e)

        return ResponseEntity.internalServerError().body("시스템 오류가 발생했습니다.")
    }

}