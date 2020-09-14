package com.skfo763.remote.util

import com.google.gson.*
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DateTypeAdapter : JsonSerializer<Date?>, JsonDeserializer<Date?> {

    companion object {
        private var dateFormatList: MutableList<SimpleDateFormat>? = null

        init {
            dateFormatList = ArrayList()
            dateFormatList?.add(SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN))
            dateFormatList?.add(SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN))
        }
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        jsonElement: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        var resultException: ParseException? = null
        for (simpleDateFormat in dateFormatList!!) {
            resultException = try {
                return simpleDateFormat.parse(jsonElement.asString)
            } catch (e: ParseException) {
                e
            }
        }
        throw JsonParseException(resultException)
    }

    override fun serialize(
        date: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(dateFormatList!![0].format(date))
    }
}