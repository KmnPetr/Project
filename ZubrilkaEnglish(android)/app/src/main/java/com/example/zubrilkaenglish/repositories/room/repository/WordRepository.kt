package com.example.zubrilkaenglish.repositories.room.repository

import androidx.lifecycle.LiveData
import com.example.zubrilkaenglish.models.Word

interface WordRepository {
    val allWords:LiveData<List<Word>>
    suspend fun insertWord(word: Word,onSuccess:()->Unit)
    suspend fun deleteWord(word: Word,onSuccess:()->Unit)
}