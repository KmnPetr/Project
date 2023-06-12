package com.example.zubrilkaenglish.repositories.web

import com.example.zubrilkaenglish.models.Word
import retrofit2.http.GET

interface WebWordRepository {
@GET("/words")
suspend fun getAllWords():List<Word>
}