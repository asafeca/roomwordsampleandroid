package com.example.roomwordsample.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "last_update") val last_update: String?= Date().time.toString()

)