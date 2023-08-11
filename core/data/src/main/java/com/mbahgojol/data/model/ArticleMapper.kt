package com.mbahgojol.data.model

import com.mbahgojol.model.entities.ArticleEntities
import com.mbahgojol.network.dtos.ArticleDto

fun ArticleDto.toArticleEntities() = ArticleEntities(
    author.orEmpty(), content, description, publishedAt, title, url, urlToImage.orEmpty(),
)
