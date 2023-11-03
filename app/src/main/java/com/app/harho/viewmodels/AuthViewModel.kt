package com.app.harho.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.harho.model.auth.login.LoginPramModel
import com.app.harho.model.auth.login.LoginResponse
import com.app.harho.model.auth.signup.SignUpPramModel
import com.app.harho.model.auth.signup.SignUpResponse
import com.app.harho.model.auth.userme.UserMeResponse
import com.app.harho.network.Repository
import com.app.harho.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AuthViewModel(val application: Application, val repository: Repository) : ViewModel() {

    val resultLogin = MutableLiveData<Resource<LoginResponse>>()
    val resultSignUp = MutableLiveData<Resource<SignUpResponse>>()
    val resultUserMe = MutableLiveData<Resource<UserMeResponse>>()


    fun getUserMe() {
        resultUserMe.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getUserMe()
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultUserMe.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultUserMe.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultUserMe.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultUserMe.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onLogin(body: LoginPramModel) {
        resultLogin.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.onLogin(body)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultLogin.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultLogin.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultLogin.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultLogin.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onSignUp(body: SignUpPramModel) {
        resultSignUp.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.onSignUp(body)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultSignUp.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultSignUp.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultSignUp.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultSignUp.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }
}