package com.example.zubrilkaenglish.repositories.retrofit

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.zubrilkaenglish.models.Word
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class RetrofitService {
    suspend fun getAllWords():List<Word>?{
        return RetrofitInstance.wordApi.getAllWords().body()//TODO там приходит response его нужно обработать здесь
    }

    suspend fun getUpdateAt(): String? {
        return RetrofitInstance.propApi.getUpdateAt().body()?.value
    }
}