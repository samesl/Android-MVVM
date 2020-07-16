package com.android.saman.sample.androidmvvm.repository

import com.android.saman.sample.androidmvvm.persistence.room.entity.SampleEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPIs {

    //TODO : TO BE COMPLETED
    @GET()
    fun getCall() : Single<Response<SampleEntity>>

    @GET()
    fun getSecondCall()
}