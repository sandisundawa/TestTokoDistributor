package com.example.tokodistributorsandi.data.repository

import androidx.lifecycle.LiveData
import com.example.tokodistributorsandi.data.api.TokoDistributorInterface
import com.example.tokodistributorsandi.data.model.CategoryResponse
import com.example.tokodistributorsandi.data.repository.CategoryDataSource
import com.example.tokodistributorsandi.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class CategoryRepository (private val apiService : TokoDistributorInterface) {

    lateinit var categoryDataSource: CategoryDataSource

    fun fetchCategory (compositeDisposable: CompositeDisposable, withStaple: Boolean) : LiveData<CategoryResponse> {

        categoryDataSource = CategoryDataSource(apiService,compositeDisposable)
        categoryDataSource.fetchCategory(withStaple)

        return categoryDataSource.categoryResponse

    }

    fun getCategoryNetworkState(): LiveData<NetworkState> {
        return categoryDataSource.networkState
    }
}