package com.android.saman.sample.androidmvvm.di.components

import com.android.saman.sample.androidmvvm.MainActivity
import com.android.saman.sample.androidmvvm.di.modules.ActivityModule
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}