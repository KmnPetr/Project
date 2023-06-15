package com.example.zubrilkaenglish.screens.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zubrilkaenglish.models.Word
import com.example.zubrilkaenglish.repositories.retrofit.WebRepository
import com.example.zubrilkaenglish.repositories.room.RoomRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val webRepository=WebRepository()
    private val roomRepository=RoomRepository()

    val listAllWords: MutableLiveData<List<Word>> = MutableLiveData()


    fun getListAllWords(): LiveData<List<Word>> {
        viewModelScope.launch {
            val list=webRepository.getAllWords()
            if (list != null) {
                println("получили список с инета. Его размер = "+list.size)
            }

            if (list != null) {
                roomRepository.deleteAllWords()
                println("удалили старый список из БД")
                roomRepository.insertListWords(list)
                println("положили список в БД")
            }else{
                println("список с сервера был null")
            }

            val list2=roomRepository.getAllWords()//TODO посмотри
            println("получили список из БД. Его размер: "+list2.size)

            listAllWords.value=list2
            println("получили список из бд и отправили его на экран...")
            println("Размер списка: "+ listAllWords.value!!.size)
        }

        return listAllWords
    }
}