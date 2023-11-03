package com.app.harho.view.welcome.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.harho.R
import com.app.harho.base.BaseFragment
import com.app.harho.databinding.FragmentHumidityBinding
import com.app.harho.databinding.FragmetWateringBinding
import com.app.harho.view.MainActivity


class FragmentWatering : BaseFragment(), View.OnClickListener {
    lateinit var binding: FragmetWateringBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmetWateringBinding.inflate(layoutInflater)
        binding.onClick = this
        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.back ->{
                findNavController().popBackStack()
            }
        }
    }


}