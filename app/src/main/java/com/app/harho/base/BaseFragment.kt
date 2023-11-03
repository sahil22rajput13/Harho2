package com.app.harho.base

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.harho.network.Repository

open class BaseFragment:Fragment(){
    companion object {
        val application = Application()
        val repository = Repository()

    }
}