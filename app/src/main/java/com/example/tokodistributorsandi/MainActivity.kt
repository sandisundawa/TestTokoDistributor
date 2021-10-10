package com.example.tokodistributorsandi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tokodistributorsandi.adapter.CategoryAdapter
import com.example.tokodistributorsandi.adapter.ProductPagedAdapter
import com.example.tokodistributorsandi.data.api.TokoDistributorClient
import com.example.tokodistributorsandi.data.api.TokoDistributorInterface
import com.example.tokodistributorsandi.data.repository.BannerRepository
import com.example.tokodistributorsandi.data.repository.CategoryRepository
import com.example.tokodistributorsandi.data.repository.NetworkState
import com.example.tokodistributorsandi.data.repository.ProductPageListRepository
import com.example.tokodistributorsandi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    lateinit var productPageListRepository: ProductPageListRepository
    lateinit var categoryRepository: CategoryRepository
    lateinit var bannerRepository: BannerRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val apiService: TokoDistributorInterface = TokoDistributorClient.getClient()

        productPageListRepository = ProductPageListRepository(apiService)
        categoryRepository = CategoryRepository(apiService)
        bannerRepository = BannerRepository(apiService)

        viewModel = getViewModel()

        setupBanner()
        setupCategory()
        setupProduct()

    }

    private fun setupBanner() {
        viewModel.banner.observe(this, Observer {
            val bannerListItem = it.data.map { it.urlMobile }

            binding.cvCarousel.setImageListener { position, imageView ->
                Glide.with(this).load(bannerListItem[position]).into(imageView)
            }
            binding.cvCarousel.pageCount = bannerListItem.size

        })
    }

    private fun setupCategory() {
        viewModel.categogy.observe(this, Observer {
            val categoryAdapter = CategoryAdapter(it.data, this)
            binding.rvCategory.adapter = categoryAdapter
            binding.rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)

        })
    }

    private fun setupProduct() {
        val productAdapter = ProductPagedAdapter(this)

        val gridLayoutManager = GridLayoutManager(this, 2)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = productAdapter.getItemViewType(position)
                if (viewType == productAdapter.PRODUCT_VIEW_TYPE) return 1
                else return 2
            }
        }

        binding.rvProduk.layoutManager = gridLayoutManager
        binding.rvProduk.setHasFixedSize(true)
        binding.rvProduk.adapter = productAdapter

        viewModel.productPagedList.observe(this, Observer {
            productAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            binding.progressBar.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            binding.txtError.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                productAdapter.setNetworkState(it)
            }
        })
    }

    private fun getViewModel(): MainActivityViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(productPageListRepository, categoryRepository, bannerRepository) as T
            }
        })[MainActivityViewModel::class.java]
    }
}