package com.example.zubrilkaenglish.repositories.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zubrilkaenglish.models.Word
import com.example.zubrilkaenglish.utils.APP

@Database(entities = [Word::class], version = 1)
abstract class DataBase:RoomDatabase(){

    abstract fun getWordDAO(): WordDAO

    companion object{

        private var database: DataBase?=null

        @Synchronized
        fun getInstanseDB(/*context: Context*/):DataBase{
            var context= APP
            return if(database==null){
                database= Room.databaseBuilder(context,DataBase::class.java,"DataBase").build()
                return database as DataBase
            }else{
                return database as DataBase
            }
        }
    }
}