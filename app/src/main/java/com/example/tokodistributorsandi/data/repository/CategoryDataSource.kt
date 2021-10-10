package com.example.tokodistributorsandi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tokodistributorsandi.data.api.TokoDistributorInterface
import com.example.tokodistributorsandi.data.model.CategoryResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryDataSource (private val apiService : TokoDistributorInterface, private val compositeDisposable: CompositeDisposable) {

    private val _networkState  = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _categoryResponse =  MutableLiveData<CategoryResponse>()
    val categoryResponse: LiveData<CategoryResponse>
        get() = _categoryResponse

    fun fetchCategory(withStaple: Boolean) {

        _networkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiService.getCategory(withStaple)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _categoryResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("CategoryDataSource", it.message.orEmpty())
                        }
                    )
            )

        }

        catch (e: Exception){
            Log.e("CategoryDataSource",e.message.orEmpty())
        }
    }
}