package com.android.saman.sample.androidmvvm.di.modules

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.room.Room
import com.android.saman.sample.androidmvvm.MainApplication
import com.android.saman.sample.androidmvvm.di.scopes.ApplicationScope
import com.android.saman.sample.androidmvvm.persistence.room.AndroidDatabase
import com.android.saman.sample.androidmvvm.repository.RetrofitAPIs
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//Fifth step of setting up dagger 2
@Module
class ApplicationModule(private val app: MainApplication) {


    @Provides
    @ApplicationScope
    fun provideApplication() = app

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(): SharedPreferences = app.getSharedPreferences(SHARED_PREFERENCE_NAME ,MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideResources(): Resources = app.resources

    @Provides
    @ApplicationScope
    fun provideDatabase() : AndroidDatabase {
        return Room.databaseBuilder(app.applicationContext,
            AndroidDatabase::class.java, ANDROID_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit() : RetrofitAPIs {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(RetrofitAPIs::class.java)
    }


    companion object {
        private const val SHARED_PREFERENCE_NAME = "Android_SAMPLE"
        private const val ANDROID_DATABASE_NAME = "Android_DATABASE"
        private const val BASE_URL = "" //TODO: TO BE REPLACED WITH A REAL URL
    }
}