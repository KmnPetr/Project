package com.example.zubrilkaenglish.repositories

import com.example.zubrilkaenglish.models.Word
import com.example.zubrilkaenglish.repositories.retrofit.RetrofitService
import com.example.zubrilkaenglish.repositories.room.RoomService

class Repository {
    private val retrofitService=RetrofitService()
    private val roomService=RoomService()

    /**
     * функция получит список Words из сети и положит его в БД из БД вернет новые данные
     */
    suspend fun getAllWords():List<Word>{

        checkUpdateAtOnServer()

        val list=retrofitService.getAllWords()
        if (list != null) {
            println("получили список с инета. Его размер = "+list.size)
            roomService.deleteAllWords()
            println("удалили старый список из БД")
            roomService.insertListWords(list)
            println("положили список в БД")
        }else{
            println("список с сервера был null")
        }
        return roomService.getAllWords()
    }
    private suspend fun checkUpdateAtOnServer():Boolean{
        val serverUpAt: String? =retrofitService.getUpdateAt()
        println(serverUpAt)
        val roomUpAt:String=roomService.getUpdateAt()
        println(roomUpAt)
        return true
    }
}