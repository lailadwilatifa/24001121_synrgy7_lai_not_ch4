package com.example.note.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tbNotes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var id: Int = 0,

    @ColumnInfo(name = "judul")
    private var judul: String? = null,

    @ColumnInfo(name = "catatan")
    private var catatan: String? = null,

    @ColumnInfo(name = "tanggal")
    private var tanggal: String? = null
) : Parcelable

