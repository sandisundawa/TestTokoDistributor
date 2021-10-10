package com.example.tokodistributorsandi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tokodistributorsandi.data.api.TokoDistributorInterface
import com.example.tokodistributorsandi.data.model.BannerResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BannerDateSource (private val apiService : TokoDistributorInterface, private val compositeDisposable: CompositeDisposable) {

    private val _networkState  = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _bannerResponse =  MutableLiveData<BannerResponse>()
    val bannerResponse: LiveData<BannerResponse>
        get() = _bannerResponse

    fun fetchBanner() {

        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getBanner()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _bannerResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("BannerDataSource", it.message.orEmpty())
                        }
                    )
            )

        }

        catch (e: Exception){
            Log.e("BannerDataSource",e.message.orEmpty())
        }
    }
}