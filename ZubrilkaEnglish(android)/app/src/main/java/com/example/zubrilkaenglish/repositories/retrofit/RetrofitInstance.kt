package com.example.zubrilkaenglish.repositories.retrofit

import com.example.zubrilkaenglish.utils.URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}//TODO забыл интерсептор