package com.android.saman.sample.androidmvvm.repository

import com.android.saman.sample.androidmvvm.persistence.room.AndroidDatabase
import com.android.saman.sample.androidmvvm.persistence.room.SampleDao
import com.android.saman.sample.androidmvvm.persistence.room.SampleEntity
import javax.inject.Inject

class BaseAndroidRepository @Inject constructor(database: AndroidDatabase) {

    private val sampleDao : SampleDao = database.sampleDao()

    fun insertIntoDatabase(sampleEntity: SampleEntity){
        sampleDao.insertSample(sampleEntity)
    }

    fun listAllSampleEntities() = sampleDao.getSampleList()

}