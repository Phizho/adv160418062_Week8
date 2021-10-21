package com.ubaya.todoapp.Model

import androidx.room.*

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String
    ) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}
