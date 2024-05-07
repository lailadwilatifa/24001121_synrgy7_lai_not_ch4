package com.example.note.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "judul")
    var judul: String? = null,

    @ColumnInfo(name = "catatan")
    var catatan: String? = null,

    @ColumnInfo(name = "tanggal")
    var tanggal: String? = null
) : Parcelable

