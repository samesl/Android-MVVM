package com.android.saman.sample.androidmvvm.repository

import retrofit2.http.GET

interface RetrofitAPIs {

    //TODO : TO BE COMPLETED
    @GET()
    fun getCall()

    @GET()
    fun getSecondCall()
}