package com.app.harho.view.welcome.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.harho.base.BaseActivity
import com.app.harho.base.GetObjects
import com.app.harho.databinding.ActivitySplashScreenBinding
import com.app.harho.utils.SharedPreference
import com.app.harho.utils.Status.ERROR
import com.app.harho.utils.Status.LOADING
import com.app.harho.utils.Status.SUCCESS
import com.app.harho.view.auth.LoginActivity
import com.app.harho.view.auth.SignupActivity
import com.app.harho.view.welcome.BoxArrivedActivity
import com.app.harho.view.welcome.CheckListActivity
import com.app.harho.view.welcome.FinalReadyActivity
import com.app.harho.view.welcome.IntroActivity
import com.app.harho.viewmodels.AuthViewModel
import com.app.harho.viewmodels.ViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : BaseActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    val viewModel by viewModels<AuthViewModel> { ViewModelFactory(application, repository) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUserMeApi()

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            delay(1000)
            if (GetObjects.preference.getString(SharedPreference.Key.TOKEN) == "") {
                startActivity(Intent(this@SplashScreen, SignupActivity::class.java))
            } else {
                observer()
            }
        }
    }

    private fun observer() {
        viewModel.resultUserMe.observe(this) {
            it.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        val response = it?.data?.body
                        if (response?.is_updated != 1) {
                            startActivity(Intent(this, SignupActivity::class.java))
                        } else {
                            if (response.box_arrived != 1) {
                                startActivity(Intent(this, BoxArrivedActivity::class.java))
                            } else {
                                if (response.received_everything != 1) {
                                    startActivity(Intent(this, CheckListActivity::class.java))
                                } else {
                                    if (response.ready_to_grow != 1) {
                                        startActivity(
                                            Intent(
                                                this,
                                                FinalReadyActivity::class.java
                                            )
                                        )
                                    } else {
                                        if (response.is_updated != 1) {
                                            startActivity(
                                                Intent(
                                                    this,
                                                    IntroActivity::class.java
                                                )
                                            )
                                        } else {
                                            startActivity(
                                                Intent(
                                                    this,
                                                    LoginActivity::class.java
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    LOADING -> {

                    }

                    ERROR -> {

                    }
                }
            }
        }
    }

    private fun getUserMeApi() {
        viewModel.getUserMe()
    }
}