package com.mbahgojol.common.network

import com.google.gson.Gson
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

suspend fun <T> HttpResponse.toDto(clazz: Class<T>): T =
    Gson().fromJson(bodyAsText(), clazz)


