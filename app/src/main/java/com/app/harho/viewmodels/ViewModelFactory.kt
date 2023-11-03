package com.app.harho.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.harho.network.Repository

class ViewModelFactory(
    private val application: Application,
    private val repository: Repository,
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                return AuthViewModel(application, repository) as T
            }

            modelClass.isAssignableFrom(UpdateViewModel::class.java) -> {
                return UpdateViewModel(application, repository) as T
            }

            modelClass.isAssignableFrom(PropogatorViewModel::class.java) -> {
                return PropogatorViewModel(application, repository) as T
            }


            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }


    }

}