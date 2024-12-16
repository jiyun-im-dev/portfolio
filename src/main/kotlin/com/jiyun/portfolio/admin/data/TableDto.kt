package com.jiyun.portfolio.admin.data

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

data class TableDto(
    val name: String,
    val columns: List<String>,
    val records: List<List<String>>
) {

    companion object {
        fun <T : Any> from(classInfo: KClass<T>, entities: List<Any>, vararg filterings: String): TableDto {
            // 테이블명은 클래스명에서 가져옴. 클래스명이 존재하지 않는 경우 "unknown"
            val name = classInfo.simpleName ?: "unknown"
            // 테이블의 컬럼 정보는 엔티티 클래스에서 가져옴
            val columns = createColumns(classInfo, *filterings)
            // 테이블의 레코드 정보는 조회한 엔티티 리스트에서 가져옴
            val records = entities.map { entity ->
                columns.map { column ->
                    classInfo.memberProperties.find { column.equals(it.name) }?.getter?.call(entity).toString()
                }.toList()
            }.toList()

            return TableDto(name = name, columns = columns, records = records)
        }

        // 컬럼명 가져오기
        private fun <T : Any> createColumns(classInfo: KClass<T>, vararg filterings: String): MutableList<String> {
            val mainColumns = classInfo.java.declaredFields
                .filter { !filterings.contains(it.name) }
                .map { it.name }.toMutableList()
            val baseColumns = classInfo.java.superclass.declaredFields
                .map { it.name }.toMutableList()

            return (mainColumns + baseColumns).toMutableList()
        }
    }

}