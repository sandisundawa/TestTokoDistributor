package com.example.tokodistributorsandi.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.tokodistributorsandi.data.api.TokoDistributorInterface
import com.example.tokodistributorsandi.data.model.Product
import io.reactivex.disposables.CompositeDisposable

class ProductDataSourceFactory (private val apiService : TokoDistributorInterface, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Product>() {

    val productLiveDataSource =  MutableLiveData<ProductDataSource>()

    override fun create(): DataSource<Int, Product> {
        val productDataSource = ProductDataSource(apiService,compositeDisposable)

        productLiveDataSource.postValue(productDataSource)
        return productDataSource
    }
}