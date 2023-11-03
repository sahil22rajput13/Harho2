package com.app.harho.adapter.propogatorProduct

import android.content.BroadcastReceiver
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.harho.databinding.ItemPropogatorProductDetailBinding
import com.bumptech.glide.Glide

class PropogatorDetailProductRecyclerAdapter(
    val context: Context,
    val body: ArrayList<com.app.harho.model.propogator.productDetail.Body>,
    val callbacks: PropogatorProductDetailRecyclerAdapterCallBack
) : RecyclerView.Adapter<PropogatorDetailProductRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPropogatorProductDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getData(position: Int) {
            Glide.with(context).load(body[position].image).into(binding.ivProduct)
            binding.tvProduct.text = body[position].tittle
            itemView.setOnClickListener {
              callbacks.onClickItem(
                body[position].tittle,body[position].id.toString())
            }
        }

    }

    interface PropogatorProductDetailRecyclerAdapterCallBack {
        fun onClickItem(tittle: String, receiverId:String)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PropogatorDetailProductRecyclerAdapter.ViewHolder {
        return ViewHolder(
            ItemPropogatorProductDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: PropogatorDetailProductRecyclerAdapter.ViewHolder,
        position: Int
    ) {
        holder.getData(position)
    }

    override fun getItemCount(): Int {
        return body.size
    }
}