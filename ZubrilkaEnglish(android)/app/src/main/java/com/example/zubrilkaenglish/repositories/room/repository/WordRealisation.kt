package com.example.zubrilkaenglish.repositories.room.repository

import androidx.lifecycle.LiveData
import com.example.zubrilkaenglish.models.Word
import com.example.zubrilkaenglish.repositories.room.DAO.WordDAO

class WordRealisation(private val wordDAO: WordDAO) : WordRepository{
    override val allWords: LiveData<List<Word>>
        get() = wordDAO.getAllWords()


    override suspend fun insertWord(word: Word, onSuccess: () -> Unit) {
        wordDAO.insert(word)
        onSuccess()
    }

    override suspend fun deleteWord(word: Word, onSuccess: () -> Unit) {
        wordDAO.delete(word)
        onSuccess()
    }
}