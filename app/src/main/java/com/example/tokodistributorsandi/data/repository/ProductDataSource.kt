package com.example.tokodistributorsandi.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.tokodistributorsandi.data.api.FIRST_PAGE
import com.example.tokodistributorsandi.data.api.TokoDistributorInterface
import com.example.tokodistributorsandi.data.model.Product
import com.example.tokodistributorsandi.data.model.ProductResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductDataSource (private val apiService : TokoDistributorInterface, private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, Product>(){

    private var page = FIRST_PAGE

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {

        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getProduct(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.data, null, page+1)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("ProductDataSource", it.stackTraceToString())
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getProduct(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if(20 >= params.key) {
                            callback.onResult(it.data, params.key+1)
                            networkState.postValue(NetworkState.LOADED)
                        }
                        else{
                            networkState.postValue(NetworkState.ENDOFLIST)
                        }
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("ProductDataSource", it.stackTraceToString())
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {

    }
}