package com.example.zubrilkaenglish.dataService

import com.example.zubrilkaenglish.models.Word
import com.example.zubrilkaenglish.repositories.retrofit.RetrofitRepository
import com.example.zubrilkaenglish.repositories.room.RoomRepository

class DataService {
    private val retrofitRepository=RetrofitRepository()
    private val roomRepository=RoomRepository()

    /**
     * функция получит список Words из сети и положит его в БД из БД вернет новые данные
     */
    suspend fun getAllWords():List<Word>{
        val list=retrofitRepository.getAllWords()
        if (list != null) {
            println("получили список с инета. Его размер = "+list.size)
            roomRepository.deleteAllWords()
            println("удалили старый список из БД")
            roomRepository.insertListWords(list)
            println("положили список в БД")
        }else{
            println("список с сервера был null")
        }
        return roomRepository.getAllWords()
    }
}