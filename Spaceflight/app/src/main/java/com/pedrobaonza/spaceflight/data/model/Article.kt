package com.pedrobaonza.spaceflight.data.model

data class Article(
    val id: Int,
    val title: String,
    val url: String,
    val image_url: String,
    val summary: String,
    val published_at: String
)