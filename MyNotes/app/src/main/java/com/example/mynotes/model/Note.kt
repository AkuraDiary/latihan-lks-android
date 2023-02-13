package com.example.mynotes.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tbl_note")
data class Note(
    @PrimaryKey
    val id:Int?,

    @ColumnInfo(name = "note_title")
    var title :String?,

    @ColumnInfo(name = "note_content")
    var content : String?,

    @ColumnInfo(name = "note_date")
    var date: String?

):Parcelable
