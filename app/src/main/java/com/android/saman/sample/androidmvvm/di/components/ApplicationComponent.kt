package com.android.saman.sample.androidmvvm.di.components

import com.android.saman.sample.androidmvvm.MainApplication
import com.android.saman.sample.androidmvvm.di.modules.ActivityModule
import com.android.saman.sample.androidmvvm.di.modules.ApplicationModule
import com.android.saman.sample.androidmvvm.di.modules.FragmentModule
import com.android.saman.sample.androidmvvm.di.scopes.ApplicationScope
import dagger.Component
import javax.inject.Singleton

//Sixth Step of setting up Dagger 2
@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

     fun plus(activityModule: ActivityModule) : ActivityComponent
     fun plus(fragmentModule: FragmentModule) : FragmentComponent

     fun inject(app: MainApplication)
}