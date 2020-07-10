package com.android.saman.sample.androidmvvm.di.modules

import com.android.saman.sample.androidmvvm.FirstFragment
import com.android.saman.sample.androidmvvm.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FragmentModule() {

    @Provides
    @FragmentScope
    fun firstFragment(): FirstFragment{
        return FirstFragment()
    }
}