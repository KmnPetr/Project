package com.example.zubrilkaenglish.repositories.room

import androidx.room.Dao
import androidx.room.Query
import com.example.zubrilkaenglish.models.PropModel

@Dao
interface PropDAO {
    @Query("SELECT*FROM prop_table WHERE prop_table.`key`='update_at'")
    suspend fun getUpdateAt():PropModel
}