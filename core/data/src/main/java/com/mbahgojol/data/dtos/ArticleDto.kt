package com.mbahgojol.data.dtos

import com.google.gson.annotations.SerializedName
import com.mbahgojol.model.entities.ArticleEntities

data class ArticleDto(
    @SerializedName("author") val author: String?,
    @SerializedName("content") val content: String,
    @SerializedName("description") val description: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("source") val source: SourceDto,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?,
)

data class SourceDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)

fun ArticleDto.toArticleEntities() = ArticleEntities(
    author.orEmpty(), content, description, publishedAt, title, url, urlToImage.orEmpty(),
)
