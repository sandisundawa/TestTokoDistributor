package com.example.tokodistributorsandi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tokodistributorsandi.R
import com.example.tokodistributorsandi.data.model.Category

class CategoryAdapter( var category: List<Category>, var context: Context) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.category_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val category = category[i]
        myViewHolder.title.text = category.categoryName
        Glide.with(context)
            .load(category.icon)
            .into(myViewHolder.imgCategory)

    }

    override fun getItemCount(): Int {
        return category.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var imgCategory: ImageView

        init {
            title = itemView.findViewById(R.id.tv_title_ct)
            imgCategory = itemView.findViewById(R.id.iv_category)
        }
    }

}