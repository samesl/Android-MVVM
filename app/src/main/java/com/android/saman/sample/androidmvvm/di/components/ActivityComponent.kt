package com.android.saman.sample.androidmvvm.di.components

import com.android.saman.sample.androidmvvm.MainActivity
import com.android.saman.sample.androidmvvm.di.modules.ActivityModule
import com.android.saman.sample.androidmvvm.di.modules.ViewModelModule
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, ViewModelModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}