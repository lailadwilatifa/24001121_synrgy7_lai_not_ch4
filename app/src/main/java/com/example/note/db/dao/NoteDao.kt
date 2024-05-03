package com.example.note.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note.db.entities.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("DELETE FROM tb_notes WHERE id = :key")
    fun deleteNote(key: Int)

    @Query("SELECT * From tb_notes")
    fun getAllNotes():LiveData<List<Note>>
}