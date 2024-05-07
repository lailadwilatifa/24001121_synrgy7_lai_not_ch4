package com.example.note.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.note.db.dao.NoteDao
import com.example.note.db.entities.Note

class NoteRepository (private val noteDao: NoteDao ){
    fun insert(item: Note){
        InsertItemAsyncTask(noteDao).execute(item)
    }

    fun delete(key: Int){
        DeleteItemAsyncTask(noteDao).execute(key)
    }

    fun update(item: Note){
        UpdateItemAsyncTask(noteDao).execute(item)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    private class InsertItemAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
        override fun doInBackground(vararg params: Note?) {
            noteDao.insertNote(params[0]!!)
        }
    }

    private class DeleteItemAsyncTask(val noteDao: NoteDao) : AsyncTask<Int, Unit, Unit>() {
        override fun doInBackground(vararg params: Int?) {
            noteDao.deleteNote(params[0]!!)
        }
    }

    private class UpdateItemAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
        override fun doInBackground(vararg params: Note?) {
            noteDao.updateNote(params[0]!!)
        }
    }
}