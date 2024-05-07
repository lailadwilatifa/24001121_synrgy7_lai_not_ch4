package com.example.note.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note.db.dao.NoteDao
import com.example.note.db.entities.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase(){

    abstract val noteDao : NoteDao

    companion object {

        @Volatile
        var INSTANCE: NoteDB? = null

        fun getInstance(context: Context): NoteDB {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, NoteDB::class.java, "myDB").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}