package com.app.harho.adapter.propogator

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.harho.databinding.ItemPropogatorTittleBinding
import com.app.harho.model.propogator.propogatorTittle.Propacatorinfo
import com.app.harho.utils.gone
import com.app.harho.utils.visible

class PropogatorTittleAdapter(
    val context: Context,
    val body: ArrayList<Propacatorinfo>
) : RecyclerView.Adapter<PropogatorTittleAdapter.ViewHolder>() {
    var selectedPosition = 0



    inner class ViewHolder(val binding: ItemPropogatorTittleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun getData(position: Int) {
            binding.tvTittleVisible.text = body[position].name
            binding.tvTittleGone.text = body[position].name
            when (position) {
                2 -> {
                    binding.ivPropogatorLine.gone()

                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPropogatorTittleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return body.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getData(position)
            if (selectedPosition == position||position<selectedPosition) {
                holder.binding.rlPropogatorTittleVisible.visible()
                holder.binding.tvTittleVisible.visible()
            }else{
                holder.binding.rlPropogatorTittleVisible.gone()
            }

    }

}



