package com.app.harho.adapter.propogatorProduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.harho.databinding.ItemPropogatorProductBinding
import com.bumptech.glide.Glide
import com.app.harho.model.propogator.propogatorProduct.Body

class PropogatorProductRecyclerAdapter(
    val context: Context,
    val body: ArrayList<Body>,
    val callbacks: PropogatorProductRecyclerAdapterCallBack
) : RecyclerView.Adapter<PropogatorProductRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPropogatorProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getData(position: Int) {
            Glide.with(context).load(body[position].image).into(binding.ivHome)
            binding.tvPropagator.text = body[position].name
            binding.btnArrow1.setOnClickListener {
               callbacks.onClickItem(body[position].name.toString(),
                   body[position].id.toString())
            }
        }

    }

    interface PropogatorProductRecyclerAdapterCallBack {
        fun onClickItem(categoryName: String,categoryId :String)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PropogatorProductRecyclerAdapter.ViewHolder {
        return ViewHolder(
            ItemPropogatorProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: PropogatorProductRecyclerAdapter.ViewHolder,
        position: Int
    ) {
        holder.getData(position)
    }

    override fun getItemCount(): Int {
        return body.size
    }
}