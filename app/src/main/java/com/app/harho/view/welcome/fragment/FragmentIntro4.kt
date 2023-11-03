package com.app.harho.view.welcome.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.harho.databinding.FragmentIntro4Binding

class FragmentIntro4 : Fragment() {
    lateinit var binding: FragmentIntro4Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentIntro4Binding.inflate(layoutInflater)
        return binding.root
    }

}