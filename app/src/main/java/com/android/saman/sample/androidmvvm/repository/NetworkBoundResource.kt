package com.android.saman.sample.androidmvvm.repository

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.atomic.AtomicInteger

// ResultType: Type for the Resource data.
// RequestType: Type for the API response.
abstract class NetworkBoundResource<ResultType, RequestType>(
    networkConnectivity: NetworkConnectivity,
    emitter: FlowableEmitter<Resource<ResultType>>
) {

    private val fetchRemote by lazy {
        shouldFetchFromRemote()
    }

    private val requestFromServer by lazy {
        requestFromServer()
    }

    init {
        val compositeDisposable = CompositeDisposable()

        if (fetchRemote) {
            compositeDisposable.add(requestFromServer
                .retryWhen { networkThrowable ->
                    val counter = AtomicInteger()
                    networkThrowable
                        .takeWhile { counter.getAndIncrement() != 3 }
                        .flatMap {
                            networkConnectivity.observeNetworkConnectivity()
                                .toFlowable(BackpressureStrategy.LATEST)
                        }
                        .filter { connected -> connected }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe({ networkResponse ->
                    Timber.d("_______Entered in on subscribe!!");
                    if (networkResponse.isSuccessful) {
                        saveCallResult(mapper().apply(networkResponse.body()!!))
                    }
                }, { throwable -> Timber.w(throwable) })
            )
        }

        emitter.setCancellable {
            Timber.d(NetworkBoundResource::class.simpleName)
            compositeDisposable.dispose();
        }


    }

    abstract fun mapper(): Function<RequestType, ResultType>

    // Called to save the result of the API response into the database
    protected abstract fun saveCallResult(item: ResultType)

    // Called to get the cached data from the database.
    protected abstract fun loadFromDb(): Flowable<ResultType>

    // Called to create the API call.
    protected abstract fun requestFromServer(): Single<Response<RequestType>>

    // Called with the data in the database to decide whether to fetch
// potentially updated data from the network.
    protected abstract fun shouldFetchFromRemote(): Boolean

}
