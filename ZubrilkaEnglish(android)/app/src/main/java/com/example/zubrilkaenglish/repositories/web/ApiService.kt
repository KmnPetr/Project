package com.example.zubrilkaenglish.repositories.web

import com.example.zubrilkaenglish.models.Word
import retrofit2.http.GET

interface ApiService {
@GET("/words")
suspend fun getAllWords():List<Word>
}