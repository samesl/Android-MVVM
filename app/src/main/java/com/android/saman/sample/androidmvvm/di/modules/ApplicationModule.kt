package com.android.saman.sample.androidmvvm.di.modules

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import com.android.saman.sample.androidmvvm.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//Fifth step of setting up dagger 2
@Module
class ApplicationModule(private val app: MainApplication) {


    @Provides
    @Singleton
    fun provideApplication() = app

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = app.getSharedPreferences(SHARED_PREFERENCE_NAME ,MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideResources(): Resources = app.resources

    companion object
    {
        private const val SHARED_PREFERENCE_NAME = "Android_SAMPLE"
    }
}