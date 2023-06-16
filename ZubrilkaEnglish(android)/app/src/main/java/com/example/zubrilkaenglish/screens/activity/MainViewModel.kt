package com.example.zubrilkaenglish.screens.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zubrilkaenglish.dataService.DataService
import com.example.zubrilkaenglish.models.Word
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val dataService=DataService()

    val listAllWords: MutableLiveData<List<Word>> = MutableLiveData()

    fun getListAllWords(): LiveData<List<Word>> {
        viewModelScope.launch {
            listAllWords.value=dataService.getAllWords()
            println("получили список из dataService")
        }
        return listAllWords
    }
}