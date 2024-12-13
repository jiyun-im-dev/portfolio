package com.jiyun.portfolio.admin.data

data class TableDto(
    val name: String,
    val columns: List<String>,
    val records: List<List<String>>
) {

}