package com.example.zubrilkaenglish.repositories.room

import com.example.zubrilkaenglish.models.PropModel
import com.example.zubrilkaenglish.models.Word

class RoomService{

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

    suspend fun getUpdateAt(): String {
        return dataBase.getPropDAO().getUpdateAt().value
    }
}