package com.app.harho.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.app.harho.R
import com.app.harho.base.BaseActivity
import com.app.harho.base.GetObjects
import com.app.harho.base.MyApplication
import com.app.harho.databinding.FragmentPropogatorRocketBinding
import com.app.harho.utils.SharedPreference
import com.app.harho.utils.Status.ERROR
import com.app.harho.utils.Status.LOADING
import com.app.harho.utils.Status.SUCCESS
import com.app.harho.utils.getCurrentDateString
import com.app.harho.utils.getCurrentTimeInAMPM
import com.app.harho.utils.getDateDifferenceInDays
import com.app.harho.utils.inVisible
import com.app.harho.utils.visible
import com.app.harho.viewmodels.PropogatorViewModel
import com.app.harho.viewmodels.ViewModelFactory
import com.bumptech.glide.Glide


class PropogatorRocketActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: FragmentPropogatorRocketBinding
    lateinit var sharedPreferences: SharedPreference
    val viewModel by viewModels<PropogatorViewModel> { ViewModelFactory(application, repository) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPropogatorRocketBinding.inflate(layoutInflater)
        binding.onClick = this
        animatorGif()
        propogatorApi()
        setContentView(binding.root)
        observer()
    }


    private fun propogatorApi() {

        viewModel.onPropogatorRocket("1")
    }

    private fun animatorGif() {
        Glide.with(this).load(R.drawable.rocket_plant).into(binding.ivRocketPlant)
        Glide.with(this).load(R.drawable.rocket_plant).into(binding.ivRocketPlantGreen)
        Glide.with(this).load(R.drawable.rocket_plant).into(binding.ivRocketPlantRed)
        Glide.with(this).load(R.drawable.rocket_plant).into(binding.ivRocketPlant2)
        Glide.with(this).load(R.drawable.rocket_plant).into(binding.ivRocketPlantGreen2)
        Glide.with(this).load(R.drawable.rocket_plant).into(binding.ivRocketPlantRed2)
        Glide.with(this).load(R.drawable.rocket_planting).into(binding.ivRocketPlanting)
        Glide.with(this).load(R.drawable.rocket_planting).into(binding.ivRocketPlanting2)
        Glide.with(this).load(R.drawable.rocket_planting).into(binding.ivRocketPlanting3)
    }


    private fun observer() {
        viewModel.resultPropogatorRocket.observe(this) {
            it.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        MyApplication.hideLoader()
                        val apiDate = it?.data?.body?.soil_change_date.toString()
                        if (apiDate == getCurrentDateString()) {
                            binding.clRocket.visible()
                        }
                        val differenceInDate =
                            getDateDifferenceInDays(apiDate, getCurrentDateString()).toInt()
                        val currentTimeInAMPM = getCurrentTimeInAMPM().toString()

                        if (differenceInDate == 1) {
                            val getKey =
                                GetObjects.preference.getString(SharedPreference.Key.ISGREEN)
                                    .toString()
                            if (currentTimeInAMPM == "11:59 PM") {
                                GetObjects.preference.putString(
                                    SharedPreference.Key.ISGREEN,
                                    ""
                                ).toString()
                            }else if (getKey == "isGreen") {
                                binding.ivRocketGreen1.visible()
                            }else if (binding.ivRocket1.isEnabled) {
                                    GetObjects.preference.putString(
                                        SharedPreference.Key.ISGREEN,
                                        "isGreen"
                                    ).toString()
                                    binding.ivRocketRed1.visible()
                                    if (binding.ivRocketRed1.isEnabled) {
                                        binding.ivRocketRed1.setOnClickListener {
                                            binding.ivRocketRed1.inVisible()
                                            binding.ivRocketGreen1.visible()
                                        }
                                    }
                                }
                            }
                        if (differenceInDate == 2) {
                            val getKey =
                                GetObjects.preference.getString(SharedPreference.Key.ISGREEN)
                                    .toString()
                            if (currentTimeInAMPM == "12:01 AM") {
                                GetObjects.preference.putString(
                                    SharedPreference.Key.ISGREEN,
                                    ""
                                ).toString()
                            }else if (getKey == "isGreen") {
                                binding.ivRocketGreen1.visible()
                            }else if (binding.ivRocket1.isEnabled) {
                                    GetObjects.preference.putString(
                                        SharedPreference.Key.ISGREEN,
                                        "isGreen"
                                    ).toString()
                                    binding.ivRocketRed1.visible()
                                    if (binding.ivRocketRed1.isEnabled) {
                                        binding.ivRocketRed1.setOnClickListener {
                                            binding.ivRocketRed1.inVisible()
                                            binding.ivRocketGreen1.visible()
                                        }
                                    }
                                }
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

    override fun onClick(p0: View?) {
        when (p0) {
            binding.back -> {
                onBackPressed()
            }


        }

    }
}
