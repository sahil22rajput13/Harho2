package com.app.harho.view.welcome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.app.harho.R
import com.app.harho.databinding.ActivityIntroBinding
import com.app.harho.databinding.LayoutIntro3BottomBinding
import com.app.harho.databinding.LayoutIntro4BottomBinding
import com.app.harho.utils.inVisible
import com.app.harho.utils.visible
import com.app.harho.view.welcome.dialog.Intro3Dialog
import com.app.harho.view.welcome.dialog.Intro4Dialog
import com.app.harho.view.welcome.fragment.FragmentIntro1
import com.app.harho.view.welcome.fragment.FragmentIntro2
import com.app.harho.view.welcome.fragment.FragmentIntro3
import java.lang.Exception


class IntroActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityIntroBinding
    lateinit var binding_BottomSheet2: LayoutIntro4BottomBinding
    lateinit var binding_BottomSheet1: LayoutIntro3BottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick = this
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flIntro, FragmentIntro1())
        fragmentTransaction.commit()
        observer()
    }

    private fun observer() {

    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.btnNext -> {
                if (binding.introTrue1.isVisible) {
                    binding.introTrue1.inVisible()
                    binding.introFalse1.visible()
                    binding.introTrue2.visible()
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.flIntro, FragmentIntro2())
                    fragmentTransaction.commit()
                } else if (binding.introTrue2.isVisible) {
                    binding.introTrue2.inVisible()
                    binding.introTrue3.visible()
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.flIntro, FragmentIntro3())
                    fragmentTransaction.commit()
                } else if (binding.introTrue3.isVisible) {
                    binding.introTrue3.inVisible()
                    val dialog = Intro3Dialog()
                    binding_BottomSheet1 = LayoutIntro3BottomBinding.inflate(layoutInflater)
                    dialog.show(supportFragmentManager, "layout3")
                    binding.introTrue4.visible()
                    Log.d("dialog","success")

                    }
                else if (binding.introTrue4.isVisible) {
                    val dialog = Intro4Dialog()
                    binding_BottomSheet2 = LayoutIntro4BottomBinding.inflate(layoutInflater)
                    dialog.show(supportFragmentManager, "layout4")
                }

            }

            binding.back -> {
                if (binding.introTrue1.isVisible) {
                    binding.introTrue1.inVisible()
                    startActivity(Intent(this, FinalReadyActivity::class.java))
                } else if (binding.introTrue2.isVisible) {
                    binding.introTrue2.inVisible()
                    binding.introTrue1.visible()
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.flIntro, FragmentIntro1())
                    fragmentTransaction.commit()
                } else if (binding.introTrue3.isVisible) {
                    binding.introTrue3.inVisible()
                    binding.introTrue2.visible()
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.flIntro, FragmentIntro2())
                    fragmentTransaction.commit()
                } else if (binding.introTrue4.isVisible) {
                    binding.introTrue4.inVisible()
                    binding.introTrue3.visible()
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.flIntro, FragmentIntro3())
                    fragmentTransaction.commit()
                }
            }

        }
    }
}