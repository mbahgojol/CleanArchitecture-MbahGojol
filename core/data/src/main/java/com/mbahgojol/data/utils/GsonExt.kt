package com.mbahgojol.data.utils

import com.google.gson.Gson
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

// TODO best practice handle response to dto class
internal suspend fun <T> HttpResponse.toDto(clazz: Class<T>): T =
    Gson().fromJson(bodyAsText(), clazz)
