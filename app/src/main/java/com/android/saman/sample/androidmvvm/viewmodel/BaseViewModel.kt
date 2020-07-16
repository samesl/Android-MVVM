package com.android.saman.sample.androidmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.android.saman.sample.androidmvvm.persistence.room.entity.SampleEntity
import com.android.saman.sample.androidmvvm.repository.BaseAndroidRepository
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class BaseViewModel @Inject constructor(private val repository: BaseAndroidRepository) :
    ViewModel() {

    //empty test viewModel all should be gone

    private var testValue: String = "This means the viewModelWorks fine with Dagger 2!"
    private var compositeDisposable : CompositeDisposable = CompositeDisposable()

    fun returnTestValue() = testValue

    init {
        compositeDisposable.add(insertDataOne()
            .andThen(insertDataTwo())
            .andThen(insertDataThree())
            .subscribeOn(Schedulers.io())
            .doOnComplete { Timber.d("Fake Insertion finished! ") }
            .subscribe()
        )
    }

    private fun insertDataOne() : Completable {
        val sampleEntity =
            SampleEntity()
        sampleEntity.firstName = "Sam"
        sampleEntity.lastName = "Esl"
        return repository.insertIntoDatabase(sampleEntity)
    }

    private fun insertDataTwo(): Completable {
        val sampleEntity =
            SampleEntity()
        sampleEntity.firstName = "Tar"
        sampleEntity.lastName = "Amr"
        return repository.insertIntoDatabase(sampleEntity)
    }

    private fun insertDataThree(): Completable {
        val sampleEntity =
            SampleEntity()
        sampleEntity.firstName = "Ehs"
        sampleEntity.lastName = "Esl"
       return repository.insertIntoDatabase(sampleEntity)
    }

    fun getAllSampleData()  = repository.listAllSampleEntities()

}