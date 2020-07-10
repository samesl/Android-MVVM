package com.android.saman.sample.androidmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.android.saman.sample.androidmvvm.persistence.room.SampleEntity
import com.android.saman.sample.androidmvvm.repository.BaseAndroidRepository
import javax.inject.Inject

class BaseViewModel @Inject constructor(private val repository: BaseAndroidRepository) :
    ViewModel() {

    //empty test viewModel all should be gone

    private var testValue: String = "This means the viewModelWorks fine with Dagger 2!"

    fun returnTestValue() = testValue

    init {
        insertDataOne()
        insertDataTwo()
        insertDataThree()
    }

    private fun insertDataOne() {
        val sampleEntity = SampleEntity()
        sampleEntity.firstName = "Sam"
        sampleEntity.lastName = "Esl"
        repository.insertIntoDatabase(sampleEntity)
    }

    private fun insertDataTwo() {
        val sampleEntity = SampleEntity()
        sampleEntity.firstName = "Tar"
        sampleEntity.lastName = "Amr"
        repository.insertIntoDatabase(sampleEntity)
    }

    private fun insertDataThree() {
        val sampleEntity = SampleEntity()
        sampleEntity.firstName = "Ehs"
        sampleEntity.lastName = "Esl"
        repository.insertIntoDatabase(sampleEntity)
    }

    fun getAllSampleData() = repository.listAllSampleEntities()

}