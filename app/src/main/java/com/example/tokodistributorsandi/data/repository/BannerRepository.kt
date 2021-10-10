package com.example.tokodistributorsandi.data.repository

import androidx.lifecycle.LiveData
import com.example.tokodistributorsandi.data.api.TokoDistributorInterface
import com.example.tokodistributorsandi.data.model.BannerResponse
import com.example.tokodistributorsandi.data.repository.BannerDateSource
import com.example.tokodistributorsandi.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class BannerRepository (private val apiService : TokoDistributorInterface) {

    lateinit var bannerDataSource: BannerDateSource

    fun fetchBanner (compositeDisposable: CompositeDisposable) : LiveData<BannerResponse> {

        bannerDataSource = BannerDateSource(apiService,compositeDisposable)
        bannerDataSource.fetchBanner()

        return bannerDataSource.bannerResponse

    }

    fun getCategoryNetworkState(): LiveData<NetworkState> {
        return bannerDataSource.networkState
    }
}