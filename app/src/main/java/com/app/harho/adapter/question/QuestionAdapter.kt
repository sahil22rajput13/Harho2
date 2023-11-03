package com.app.harho.adapter.question

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.harho.R
import com.app.harho.databinding.ItemQuestionBinding
import com.app.harho.model.question.Body
import com.app.harho.model.question.DailyQuizQuestionAnswer
import com.bumptech.glide.Glide

class QuestionAdapter(
    val context: Context,
    val body: ArrayList<Body>

) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            binding.tvQuestion.text = body[position].daily_quiz_question_answer.question
            Glide.with(context).load(R.drawable.rocket_planting).into(binding.ivQuestion)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {
        return ViewHolder(
            ItemQuestionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return body.size
    }
}