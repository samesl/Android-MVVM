package com.android.saman.sample.androidmvvm.persistence.room.dao

import androidx.room.*
import com.android.saman.sample.androidmvvm.persistence.room.entity.SampleEntity
import io.reactivex.Flowable

@Dao
interface SampleDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSample(sampleEntity: SampleEntity)

    @Delete
    fun deleteSample(sampleEntity: SampleEntity)

    @Query("SELECT * from sample_table")
    fun getSampleList() : Flowable<SampleEntity>
}