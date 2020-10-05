package dz.islem.tvguide.utils

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dz.islem.tvguide.data.model.Guide
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.nio.charset.Charset
import java.util.stream.Collectors

object Parser {
    val gson = GsonBuilder().create()

    inline fun <reified T> getData(name: String) : T = gson.fromJson(retrieveData(name), type<T>())

    inline fun <reified T> type() : Type = object : TypeToken<T>() {}.type

    fun retrieveData(name: String): String {
        val stream = Parser::class.java.classLoader?.getResourceAsStream(name)
        return BufferedReader(InputStreamReader(stream, Charset.defaultCharset()))
            .lines()
            .collect(Collectors.joining(System.lineSeparator()))
    }
}