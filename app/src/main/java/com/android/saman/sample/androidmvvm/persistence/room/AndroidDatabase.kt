package com.android.saman.sample.androidmvvm.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.saman.sample.androidmvvm.persistence.room.dao.SampleDao
import com.android.saman.sample.androidmvvm.persistence.room.entity.SampleEntity

@Database(entities = [SampleEntity::class]//Entities go here
    ,version =1)
abstract class AndroidDatabase : RoomDatabase(){

    internal abstract fun sampleDao() : SampleDao
}
