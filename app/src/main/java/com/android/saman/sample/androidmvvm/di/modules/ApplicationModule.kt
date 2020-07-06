package com.android.saman.sample.androidmvvm.di.modules

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.room.Room
import com.android.saman.sample.androidmvvm.MainApplication
import com.android.saman.sample.androidmvvm.persistence.room.AndroidDatabase
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

    @Provides
    @Singleton
    fun provideDatabase() : AndroidDatabase {
        return Room.databaseBuilder(app.applicationContext,
            AndroidDatabase::class.java, ANDROID_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    companion object {
        private const val SHARED_PREFERENCE_NAME = "Android_SAMPLE"
        private const val ANDROID_DATABASE_NAME = "Android_DATABASE"
    }
}