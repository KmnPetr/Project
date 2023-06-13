package com.example.zubrilkaenglish.repositories.retrofit

import com.example.zubrilkaenglish.models.Word

class WebRepository {
    suspend fun getAllWords():List<Word>?{
        return RetrofitInstance.api.getAllWords().body()//TODO там приходит response его нужно обработать здесь
    }
}