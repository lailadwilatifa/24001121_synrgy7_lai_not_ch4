package com.example.note.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note.db.entities.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDB: RoomDatabase() {


}