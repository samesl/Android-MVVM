package com.android.saman.sample.androidmvvm.persistence.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SampleDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSample(sampleEntity: SampleEntity)

    @Delete
    fun deleteSample(sampleEntity: SampleEntity)

    @Query("SELECT * from sample_table")
    fun getSampleList() : LiveData<SampleEntity>
}