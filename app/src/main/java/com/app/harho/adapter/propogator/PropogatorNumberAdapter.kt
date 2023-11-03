package com.app.harho.adapter.propogator

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.harho.databinding.ItemPropogatorNumberBinding
import com.app.harho.model.propogator.propogatorNumber.Body
import com.app.harho.utils.gone
import com.app.harho.utils.visible


class PropogatorNumberAdapter(
    val context: Context,
    val body: ArrayList<Body>,
) : RecyclerView.Adapter<PropogatorNumberAdapter.ViewHolder>() {
    var selectedPosition = 0

    inner class ViewHolder(val binding: ItemPropogatorNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {
            binding.introGreen.text = (position+1).toString()
            binding.introWhite.text = (position+1).toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPropogatorNumberBinding.inflate(
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
        if (selectedPosition == position) {
            holder.binding.introGreen.visible()

        } else {
            holder.binding.introGreen.gone()

        }
    }
}