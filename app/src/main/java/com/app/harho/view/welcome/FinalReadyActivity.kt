package com.app.harho.view.welcome

import android.content.Intent
import android.os.BaseBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.app.harho.base.BaseActivity
import com.app.harho.base.MyApplication
import com.app.harho.databinding.ActivityFinalReadyBinding
import com.app.harho.model.auth.update.boxarrived.BoxArrivedPramModel
import com.app.harho.model.auth.update.readytogrow.ReadyToGrowPramModel
import com.app.harho.utils.Status
import com.app.harho.utils.toast
import com.app.harho.viewmodels.UpdateViewModel
import com.app.harho.viewmodels.ViewModelFactory

class FinalReadyActivity : BaseActivity(), View.OnClickListener {
    val viewModel by viewModels<UpdateViewModel> { ViewModelFactory(application, repository) }
    lateinit var binding: ActivityFinalReadyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalReadyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick = this
        observer()
    }

    private fun observer() {
        viewModel.resultReadyToGrow.observe(this){
            it.let {
                    data ->
                when(data.status){
                    Status.SUCCESS -> {
                        MyApplication.hideLoader()
                        startActivity(Intent(this, IntroActivity::class.java))
                    }
                    Status.LOADING -> {
                        MyApplication.showLoader(this)
                    }
                    Status.ERROR -> {

                    }
                }
            }

        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.btnYes -> {
                ReadyArrivedApi()

            }

            binding.btnNo -> {
                finishAffinity()
            }
        }
    }

    private fun ReadyArrivedApi() {
        val body = ReadyToGrowPramModel("ready_to_grow", "1")
        viewModel.onReadyToGrow(body)
    }
}