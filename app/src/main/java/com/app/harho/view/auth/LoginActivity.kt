package com.app.harho.view.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.activity.viewModels
import com.app.harho.base.BaseActivity
import com.app.harho.base.GetObjects
import com.app.harho.base.MyApplication
import com.app.harho.databinding.ActivityLoginBinding
import com.app.harho.model.auth.login.LoginPramModel
import com.app.harho.utils.SharedPreference
import com.app.harho.utils.Status.ERROR
import com.app.harho.utils.Status.LOADING
import com.app.harho.utils.Status.SUCCESS
import com.app.harho.view.welcome.BoxArrivedActivity
import com.app.harho.view.welcome.CheckListActivity
import com.app.harho.view.welcome.FinalReadyActivity
import com.app.harho.view.welcome.IntroActivity
import com.app.harho.view.welcome.PropogatorActivity
import com.app.harho.viewmodels.AuthViewModel
import com.app.harho.viewmodels.ViewModelFactory
import java.util.regex.Pattern

class LoginActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivityLoginBinding
    val viewModel by viewModels<AuthViewModel> { ViewModelFactory(application, repository) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick = this
        onLoginResponse()

    }

    private fun onLoginResponse() {
        viewModel.resultLogin.observe(this) {
            it.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        MyApplication.hideLoader()
                        val response = it?.data?.body
                        GetObjects.preference.putString(
                            SharedPreference.Key.TOKEN,
                            response?.token.toString()
                        )
                        GetObjects.preference.putString(
                            SharedPreference.Key.USERID,
                            response?.user.toString()
                        )
                        if (response?.user?.is_updated != 1) {
                            startActivity(Intent(this, SignupActivity::class.java))
                        } else if (response.user.box_arrived != 1) {
                            startActivity(Intent(this, BoxArrivedActivity::class.java))
                        } else if (response.user.received_everything != 1) {
                            startActivity(Intent(this, CheckListActivity::class.java))
                        } else if (response.user.ready_to_grow != 1) {
                            startActivity(Intent(this, FinalReadyActivity::class.java))
                        } else if (response.user.is_updated != 1) {
                            startActivity(Intent(this, IntroActivity::class.java))
                        } else {
                            startActivity(Intent(this, PropogatorActivity::class.java))
                        }

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

    private fun onLoginApi() {
        val email = binding.etEmail.text.toString()
        val uniqueId = binding.etUnique.text.toString()
        val token = GetObjects.preference.putString(SharedPreference.Key.TOKEN,"afdf").toString()
        val body = LoginPramModel(token, "gfdgdg", email, uniqueId)
        viewModel.onLogin(body)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnNext -> {
                if (onValidate()) {
                    onLoginApi()
                }


            }
        }
    }

    private fun onValidate(): Boolean {
        var isValid = true
        if (TextUtils.isEmpty(binding.etEmail.text.toString())) {
            isValid = false
            binding.etEmail.error = "Required Field"
        } else if (!Pattern.matches(
                Patterns.EMAIL_ADDRESS.toRegex().toString(), binding
                    .etEmail.text.toString()
            )
        ) {
            isValid = false
            binding.etEmail.error = "Enter a valid Email"
        }
        if (TextUtils.isEmpty(binding.etUnique.text.toString())) {
            isValid = false
            binding.etUnique.error = "Required Unique Reference Number"
        }
        return isValid
    }

}