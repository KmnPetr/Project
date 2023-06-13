package com.example.zubrilkaenglish.repositories.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zubrilkaenglish.models.Word

@Dao
interface WordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("SELECT*FROM allword_table")
    fun getAllWords():LiveData<List<Word>>//TODO почему не суспенд
}