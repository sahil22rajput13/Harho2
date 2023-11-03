package com.app.harho.view.welcome.fragment

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.harho.databinding.FragmentIntro1Binding
import com.app.harho.utils.spannableLightText

class FragmentIntro1 : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentIntro1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntro1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spanText()
    }
    private fun spanText() {
        val spannableString = SpannableString("Beginning your Install")
        spannableString.spannableLightText(10,15)
        binding.beginningY.text = spannableString


    }

    override fun onClick(p0: View?) {
    }


}