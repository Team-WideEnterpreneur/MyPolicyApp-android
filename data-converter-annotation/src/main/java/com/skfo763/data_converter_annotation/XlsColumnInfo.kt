package com.skfo763.data_converter_annotation

@Target(AnnotationTarget.CLASS)
annotation class XlsEntity(val tableName: String)

@Target(AnnotationTarget.FIELD)
annotation class XlsColumnInfo(
    val entityName: String
)