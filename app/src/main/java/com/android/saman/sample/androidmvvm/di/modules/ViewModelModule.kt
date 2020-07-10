package com.android.saman.sample.androidmvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.saman.sample.androidmvvm.di.ViewModelKey
import com.android.saman.sample.androidmvvm.viewmodel.BaseViewModel
import com.android.saman.sample.androidmvvm.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)  //Give it your own viewModel here
    internal abstract fun bindBaseViewModel(baseViewModel: BaseViewModel) : ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}