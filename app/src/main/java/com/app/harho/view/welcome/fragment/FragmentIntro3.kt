package com.app.harho.view.welcome.fragment

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.harho.base.BaseFragment
import com.app.harho.databinding.FragmentIntro3Binding
import com.app.harho.utils.spannableBoldColor

class FragmentIntro3 : BaseFragment(), View.OnClickListener {
    lateinit var binding: FragmentIntro3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntro3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spanText()
    }

    private fun spanText() {
        val spannableString = SpannableString("Mix for 5 minutes")
        spannableString.spannableBoldColor(7, 17)
        binding.mixFor5M.text = spannableString
    }

    override fun onClick(p0: View?) {

    }
}