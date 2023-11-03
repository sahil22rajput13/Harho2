package com.app.harho.view.welcome.fragment

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.harho.base.BaseFragment
import com.app.harho.databinding.FragmentIntro2Binding
import com.app.harho.utils.spannableLightText

class FragmentIntro2: BaseFragment(), View.OnClickListener {
    lateinit var binding: FragmentIntro2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntro2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spanText()
    }

    private fun spanText() {
        val spannableString = SpannableString("Mixing your Soil")
        spannableString.spannableLightText(6,11)
        binding.mixingY.text = spannableString


    }

    override fun onClick(p0: View?) {
    }

}