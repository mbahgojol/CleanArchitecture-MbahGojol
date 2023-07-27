package com.mbahgojol.data.dtos

import com.google.gson.annotations.SerializedName

data class ResponseNewsDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
)
