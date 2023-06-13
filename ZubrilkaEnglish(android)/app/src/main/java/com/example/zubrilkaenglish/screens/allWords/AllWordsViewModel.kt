package com.example.zubrilkaenglish.screens.allWords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zubrilkaenglish.models.Word
import com.example.zubrilkaenglish.repositories.retrofit.WebRepository
import kotlinx.coroutines.launch

class AllWordsViewModel : ViewModel() {
    val webRepository=WebRepository()
    val listAllWords:MutableLiveData<List<Word>> = MutableLiveData()
    fun requestListWordsFromInternet(){
        viewModelScope.launch {
            listAllWords.value=webRepository.getAllWords()
        }
    }
}