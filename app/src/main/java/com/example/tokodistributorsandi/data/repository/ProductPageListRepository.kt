package com.example.tokodistributorsandi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.tokodistributorsandi.data.api.POST_PER_PAGE
import com.example.tokodistributorsandi.data.api.TokoDistributorInterface
import com.example.tokodistributorsandi.data.model.Product
import com.example.tokodistributorsandi.data.repository.NetworkState
import com.example.tokodistributorsandi.data.repository.ProductDataSource
import com.example.tokodistributorsandi.data.repository.ProductDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class ProductPageListRepository (private val apiService : TokoDistributorInterface) {

    lateinit var productPagedList: LiveData<PagedList<Product>>
    lateinit var productDataSourceFactory: ProductDataSourceFactory

    fun fetchLiveProductPagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Product>> {
        productDataSourceFactory = ProductDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        productPagedList = LivePagedListBuilder(productDataSourceFactory, config).build()
        return productPagedList

    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<ProductDataSource, NetworkState>(
            productDataSourceFactory.productLiveDataSource, ProductDataSource::networkState)
    }
}