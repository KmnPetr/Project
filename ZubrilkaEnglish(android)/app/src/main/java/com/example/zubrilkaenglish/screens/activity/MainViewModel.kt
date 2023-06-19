package com.example.zubrilkaenglish.screens.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zubrilkaenglish.repositories.Repository
import com.example.zubrilkaenglish.models.Word
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository= Repository()

    val listAllWords: MutableLiveData<List<Word>> = MutableLiveData()

    fun getListAllWords(): LiveData<List<Word>> {
        viewModelScope.launch {
            listAllWords.value=repository.getAllWords()
            println("получили список из dataService")
        }
        return listAllWords
    }
}