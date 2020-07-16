package com.android.saman.sample.androidmvvm.repository

import com.android.saman.sample.androidmvvm.persistence.room.AndroidDatabase
import com.android.saman.sample.androidmvvm.persistence.room.dao.SampleDao
import com.android.saman.sample.androidmvvm.persistence.room.entity.SampleEntity
import io.reactivex.*
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class BaseAndroidRepository @Inject constructor(database: AndroidDatabase, private val networkConnectivity: NetworkConnectivity, private val retrofitAPIs: RetrofitAPIs) {

    private val sampleDao : SampleDao = database.sampleDao()

    fun insertIntoDatabase(sampleEntity: SampleEntity) : Completable{
       return Completable.fromAction{sampleDao.insertSample(sampleEntity)}
            .subscribeOn(Schedulers.io())
    }

    fun listAllSampleEntities() :Flowable<Resource<SampleEntity>>{
        return Flowable.create({emitter: FlowableEmitter<Resource<SampleEntity>> -> object :
            NetworkBoundResource<SampleEntity,SampleEntity>(networkConnectivity,emitter) {
            override fun mapper(): Function<SampleEntity, SampleEntity> {
                      return Function {response->
                           SampleEntity()
                      }
            }

            override fun saveCallResult(item: SampleEntity) {
                insertIntoDatabase(item)
            }

            override fun loadFromDb(): Flowable<SampleEntity> {
                return sampleDao.getSampleList()
            }

            override fun requestFromServer(): Single<Response<SampleEntity>> {
                return retrofitAPIs.getCall()
            }

            override fun shouldFetchFromRemote(): Boolean {
                return true
            }

        }

    },BackpressureStrategy.LATEST)
    }


//        sampleDao.getSampleList()

}