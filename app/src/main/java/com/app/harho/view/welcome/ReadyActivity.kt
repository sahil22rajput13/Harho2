package com.app.harho.view.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.harho.R
import com.app.harho.databinding.ActivityReadyBinding

class ReadyActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityReadyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick =this

    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.btnNext ->{
                startActivity(Intent(this@ReadyActivity,FinalReadyActivity::class.java))
            }
        }
    }
}