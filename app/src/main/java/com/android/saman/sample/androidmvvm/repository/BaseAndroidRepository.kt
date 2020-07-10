package com.android.saman.sample.androidmvvm.repository

import com.android.saman.sample.androidmvvm.persistence.room.AndroidDatabase
import com.android.saman.sample.androidmvvm.persistence.room.SampleDao
import com.android.saman.sample.androidmvvm.persistence.room.SampleEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BaseAndroidRepository @Inject constructor(database: AndroidDatabase) {

    private val sampleDao : SampleDao = database.sampleDao()

    fun insertIntoDatabase(sampleEntity: SampleEntity) : Completable{
       return Completable.fromAction{sampleDao.insertSample(sampleEntity)}
            .subscribeOn(Schedulers.io())
    }

    fun listAllSampleEntities() : Flowable<SampleEntity> = sampleDao.getSampleList()

}