package com.example.tokodistributorsandi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.tokodistributorsandi.data.model.BannerResponse
import com.example.tokodistributorsandi.data.model.CategoryResponse
import com.example.tokodistributorsandi.data.model.Product
import com.example.tokodistributorsandi.data.repository.BannerRepository
import com.example.tokodistributorsandi.data.repository.CategoryRepository
import com.example.tokodistributorsandi.data.repository.NetworkState
import com.example.tokodistributorsandi.data.repository.ProductPageListRepository
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    private val productRepository: ProductPageListRepository,
    private val categoryRepository: CategoryRepository,
    private val bannerRepository: BannerRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  categogy : LiveData<CategoryResponse> by lazy {
        categoryRepository.fetchCategory(compositeDisposable, true)
    }

    val  banner : LiveData<BannerResponse> by lazy {
        bannerRepository.fetchBanner(compositeDisposable)
    }


    val productPagedList: LiveData<PagedList<Product>> by lazy {
        productRepository.fetchLiveProductPagedList(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        productRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return productPagedList.value?.size == 0
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}