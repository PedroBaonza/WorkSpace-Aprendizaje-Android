package com.pedrobaonza.spaceflight.data.model

data class ArticlesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Article>
)