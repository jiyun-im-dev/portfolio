package com.jiyun.portfolio.admin.data

abstract class FormElementDto(
    val name: String,
    val size: Int,
    val type: String
)

// 테이블 관리 페이지에서 Form에 들어갈 내용을 프론트가 아닌 서버에서 관리하기 위한 용도
class TextFormElementDto(
    name: String,
    size: Int
) : FormElementDto(name = name, size = size, type = "text")

class DateFormElementDto(
    name: String,
    size: Int
) : FormElementDto(name = name, size = size, type = "date")

class SelectFormElementDto(
    name: String,
    size: Int,
    options: List<Any>
) : FormElementDto(name = name, size = size, type = "select") {
    val options: List<Any> = options
}