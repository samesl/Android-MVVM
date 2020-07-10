package com.android.saman.sample.androidmvvm.di.components

import androidx.fragment.app.FragmentActivity
import com.android.saman.sample.androidmvvm.FirstFragment
import com.android.saman.sample.androidmvvm.MainActivity
import com.android.saman.sample.androidmvvm.di.modules.ActivityModule
import com.android.saman.sample.androidmvvm.di.modules.FragmentModule
import com.android.saman.sample.androidmvvm.di.modules.ViewModelModule
import com.android.saman.sample.androidmvvm.di.scopes.FragmentScope
import dagger.Component
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class, ViewModelModule::class])
interface FragmentComponent {

    fun inject(firstFragment: FirstFragment)
}