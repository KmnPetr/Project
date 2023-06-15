package com.example.zubrilkaenglish.repositories.room

import android.content.Context
import com.example.zubrilkaenglish.models.Word

class RoomRepository{

    private val dataBase=DataBase.getInstanseDB()


    suspend fun insertListWords(listWords:List<Word>) {
            dataBase.getWordDAO().insertListWords(listWords)
    }

    suspend fun getAllWords(): List<Word> {
        return dataBase.getWordDAO().getAllWords()
    }

    suspend fun deleteAllWords(){
        dataBase.getWordDAO().deleteAllWords()
    }
}