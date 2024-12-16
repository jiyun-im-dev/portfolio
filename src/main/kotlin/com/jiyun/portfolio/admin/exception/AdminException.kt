package com.jiyun.portfolio.admin.exception

import org.springframework.http.HttpStatus

abstract class AdminException(
    httpStatus: HttpStatus,
    message: String
) : RuntimeException(message) {

    val httpStatus: HttpStatus = httpStatus

}

// 상태 코드를 자동으로 세팅
class AdminBadRequestException(message: String) : AdminException(HttpStatus.BAD_REQUEST, message)

class AdminInternalServerErrorException(message:String) : AdminException(HttpStatus.INTERNAL_SERVER_ERROR, message)