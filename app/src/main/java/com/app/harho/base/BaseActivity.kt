package com.app.harho.base

import android.app.Application
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.app.harho.network.Repository

open class BaseActivity : AppCompatActivity() {
    companion object {
        val application = Application()
        val repository = Repository()


    }

}