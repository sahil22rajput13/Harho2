package com.app.harho.adapter.propogator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.app.harho.databinding.ItemPropogatorViewpagerBinding
import com.app.harho.model.propogator.propogatorNumber.Body
import com.app.harho.utils.gone
import com.app.harho.utils.visible
import com.bumptech.glide.Glide
import java.util.Calendar

class PropogatorViewpager(
    val context: Context,
    val body: ArrayList<Body>
) : RecyclerView.Adapter<PropogatorViewpager.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPropogatorViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {
            Glide.with(context).load(body[position].image).into(binding.rectangle1)
            binding.mixingY.text = body[position].title
            binding.youLlNeed.text = body[position].short_discription
            when(position){
                1->{
                    binding.clPropogatorPosition1.visible()
                    binding.rectangle1.gone()
                    binding.mixingY.gone()
                    binding.youLlNeed.gone()
                    binding.rlBtnCalender.setOnClickListener {
                        // on below line we are getting
                        // the instance of our calendar.
                        val c = Calendar.getInstance()

                        // on below line we are getting
                        // our day, month and year.
                        val year = c.get(Calendar.YEAR)
                        val month = c.get(Calendar.MONTH)
                        val day = c.get(Calendar.DAY_OF_MONTH)

                        // on below line we are creating a
                        // variable for date picker dialog.
                        val datePickerDialog = DatePickerDialog(
                            // on below line we are passing context.
                            context,
                            { _, year, monthOfYear, dayOfMonth ->
                                // on below line we are setting
                                // date to our text view.
                                binding.selectDate.text =
                                    (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                            },
                            // on below line we are passing year, month
                            // and day for the selected date in our date picker.
                            year,
                            month,
                            day
                        )
                        // at last we are calling show
                        // to display our date picker dialog.
                        datePickerDialog.show()
                    }
                }
                2->{
                    binding.clPropogatorPosition1.gone()
                    binding.clPropogatorPosition2.visible()
                    binding.rectangle1.gone()
                    binding.mixingY.gone()
                    binding.youLlNeed.gone()

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPropogatorViewpagerBinding.inflate(
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




