package com.example.zubrilkaenglish.scrins.allWords

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zubrilkaenglish.models.Word

class AllWordsAdapter : RecyclerView.Adapter<AllWordsAdapter.AllWordHolder>() {

    var listWords= emptyList<Word>()

    class AllWordHolder(view: View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllWordHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AllWordHolder, position: Int) {
        TODO("Not yet implemented")
    }
}