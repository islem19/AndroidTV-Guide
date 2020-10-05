package dz.islem.tvguide.data

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dz.islem.tvguide.utils.Parser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.nio.charset.Charset
import java.util.stream.Collectors
import dz.islem.tvguide.data.model.Guide

object DataProvider {
    inline fun <reified T> getMappedData(name: String) = Parser.getData<T>(name)
}

