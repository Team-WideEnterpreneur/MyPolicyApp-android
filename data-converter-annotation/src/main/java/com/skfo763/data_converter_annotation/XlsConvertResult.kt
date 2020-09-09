package com.skfo763.data_converter_annotation

import kotlin.reflect.KClass

data class XlsConvertResult(
    var tableName: String = "",
    var clazz: Class<*> = Any::class.java,
    var columnList: MutableList<Triple<String, Any, Class<*>>> = mutableListOf()
)