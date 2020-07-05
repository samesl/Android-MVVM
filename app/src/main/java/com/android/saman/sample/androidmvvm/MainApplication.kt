package com.android.saman.sample.androidmvvm

import android.app.Application
import com.android.saman.sample.androidmvvm.di.components.ApplicationComponent
import com.android.saman.sample.androidmvvm.di.components.DaggerApplicationComponent
import com.android.saman.sample.androidmvvm.di.modules.ApplicationModule

//first step of setting up Dagger 2
class MainApplication: Application() {

    //Seventh Steps of Setting up Dagger 2
    //Now that we finished setting up Module and Component it's time to generate graph object in the application.
    //It needs to be done lazily.
    private val appComponent : ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    fun applicationComponent() = appComponent


}