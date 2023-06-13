package com.example.zubrilkaenglish.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "allword_table")
data class Word(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo
    val foreignWord:String,
    @ColumnInfo
    val transcription:String,
    @ColumnInfo
    val translation:String,
    @ColumnInfo
    val hasVoise: String?,
    @ColumnInfo
    val hasImage: String?,
    @ColumnInfo
    val groupWord:String,
    @ColumnInfo
    val updatedAt: LocalDateTime?
){
    override fun toString(): String {
        return "Word ($id, '$foreignWord', $transcription, '$translation', $hasVoise, $hasImage, $groupWord, $updatedAt)"
    }
}
