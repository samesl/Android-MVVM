package com.android.saman.sample.androidmvvm.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SampleEntity::class]//Entities go here
    ,version =1)
abstract class AndroidDatabase : RoomDatabase(){

    internal abstract fun sampleDao() : SampleDao
}
