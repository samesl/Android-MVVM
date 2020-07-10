package com.android.saman.sample.androidmvvm.di.modules

import com.android.saman.sample.androidmvvm.MainActivity
import com.android.saman.sample.androidmvvm.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: MainActivity) {

    @Provides
    @ActivityScope
    fun mainActivity() = activity
}