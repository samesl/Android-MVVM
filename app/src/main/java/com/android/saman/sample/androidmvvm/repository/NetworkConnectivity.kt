package com.android.saman.sample.androidmvvm.repository

import android.net.NetworkInfo
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class NetworkConnectivity @Inject constructor(private val networkConnectivityObservable: Observable<Connectivity>) {

    private var hasConnectivity: Boolean = false

    fun observeNetworkConnectivity(): Observable<Boolean> {
        return networkConnectivityObservable.map { connectivity ->
            hasConnectivity = connectivity.state() == NetworkInfo.State.CONNECTED
            hasConnectivity
        }.subscribeOn(Schedulers.io())
    }
}