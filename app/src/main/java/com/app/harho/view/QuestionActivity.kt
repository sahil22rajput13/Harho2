package com.app.harho.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.app.harho.adapter.question.QuestionAdapter
import com.app.harho.base.BaseActivity
import com.app.harho.base.MyApplication
import com.app.harho.databinding.ActivityQuestionBinding
import com.app.harho.model.question.Body
import com.app.harho.model.question.DailyQuizQuestionAnswer
import com.app.harho.utils.Status.ERROR
import com.app.harho.utils.Status.LOADING
import com.app.harho.utils.Status.SUCCESS
import com.app.harho.viewmodels.PropogatorViewModel
import com.app.harho.viewmodels.ViewModelFactory

class QuestionActivity : BaseActivity(), View.OnClickListener {
    val viewModel by viewModels<PropogatorViewModel> { ViewModelFactory(application, repository) }
    lateinit var binding: ActivityQuestionBinding
    val listDetail = ArrayList<Body>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        questionApi()
        observer()
        binding.onClick = this
        setContentView(binding.root)
    }

    private fun observer() {
        viewModel.resultQuestion.observe(this) {
            it.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        MyApplication.hideLoader()
                        listDetail.clear()
                        listDetail.addAll(it.data!!.body)
                        val questionAdapter = QuestionAdapter(this, listDetail)
                        binding.vpQuestion.adapter = questionAdapter
                    }

                    LOADING -> {
                        MyApplication.showLoader(this)
                    }

                    ERROR -> {

                    }
                }
            }
        }
    }

    private fun questionApi() {
        viewModel.onQuestion()
    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.back ->{
                onBackPressed()
            }
        }
    }
}