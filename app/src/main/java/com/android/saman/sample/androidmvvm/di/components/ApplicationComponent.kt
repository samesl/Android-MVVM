package com.android.saman.sample.androidmvvm.di.components

import com.android.saman.sample.androidmvvm.MainApplication
import com.android.saman.sample.androidmvvm.di.modules.ActivityModule
import com.android.saman.sample.androidmvvm.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

//Sixth Step of setting up Dagger 2
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

     fun plus(activityModule: ActivityModule) : ActivityComponent

     fun inject(app: MainApplication)
}