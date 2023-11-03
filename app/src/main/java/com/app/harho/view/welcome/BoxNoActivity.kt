package com.app.harho.view.welcome

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.harho.R
import com.app.harho.base.BaseActivity
import com.app.harho.databinding.ActivityBoxArrivedBinding
import com.app.harho.databinding.ActivityBoxNoBinding
import com.app.harho.utils.toast
import kotlin.system.exitProcess

class BoxNoActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityBoxNoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoxNoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick = this
    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.btnReturn ->{
                finishAffinity()
            }
        }
    }
}