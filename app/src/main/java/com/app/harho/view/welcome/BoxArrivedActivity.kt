package com.app.harho.view.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.app.harho.base.BaseActivity
import com.app.harho.base.GetObjects
import com.app.harho.databinding.ActivityBoxArrivedBinding
import com.app.harho.model.auth.update.boxarrived.BoxArrivedPramModel
import com.app.harho.utils.SharedPreference
import com.app.harho.utils.Status
import com.app.harho.utils.Status.*
import com.app.harho.viewmodels.UpdateViewModel
import com.app.harho.viewmodels.ViewModelFactory

class BoxArrivedActivity : BaseActivity(), View.OnClickListener {
    val viewModel by viewModels<UpdateViewModel> { ViewModelFactory(application, repository) }
    lateinit var binding: ActivityBoxArrivedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoxArrivedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick = this
        observer()


    }

    private fun observer() {
        viewModel.resultBoxArrived.observe(this) {
            it.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        startActivity(Intent(this, CheckListActivity::class.java))
                    }

                    LOADING -> {

                    }

                    ERROR -> {

                    }
                }
            }

        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.btnYes -> {
                BoxArrivedApi()
            }

            binding.btnNo -> {
                startActivity(Intent(this@BoxArrivedActivity, BoxNoActivity::class.java))
            }
        }
    }

    private fun BoxArrivedApi() {
        val body = BoxArrivedPramModel("box_arrived", "1")
        viewModel.onBoxArrived(body)

    }
}