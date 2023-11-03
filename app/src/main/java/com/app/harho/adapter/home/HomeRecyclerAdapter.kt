package com.app.harho.adapter.home

import android.app.Activity
import android.content.ComponentCallbacks
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.harho.databinding.ItemHomeFragmentBinding
import com.app.harho.model.propogator.propogatorTittle.Body
import com.app.harho.model.propogator.propogatorTittle.Propacatorinfo
import com.bumptech.glide.Glide
import kotlin.math.log

class HomeRecyclerAdapter(
    val context:Context,
    val body: ArrayList<Propacatorinfo>,
    val callbacks: HomeRecyclerAdapterCallBack
):RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemHomeFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            Glide.with(context).load(body[position].image).into(binding.ivHome)
            binding.tvPropagator.text = body[position].name
            binding.btnArrow.setOnClickListener {
                callbacks.onItemClick(body[position].id.toString(),
                body[position].name.toString())
            }

        }

    }

    interface HomeRecyclerAdapterCallBack {
        fun onItemClick(id: String,name: String)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return body.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }
}