package com.android.saman.sample.androidmvvm.persistence.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sample_table")
class SampleEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int =0
    @ColumnInfo(name = "firstName")
    var firstName:String = ""
    @ColumnInfo(name = "lastName")
    var lastName: String = ""
}