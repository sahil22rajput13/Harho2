package com.app.harho.view.welcome.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.app.harho.base.BaseFragment
import com.app.harho.databinding.FragmentWebViewBinding
import com.app.harho.utils.BUSINESS_ID
import com.app.harho.utils.CATEGORY_ID
import com.app.harho.utils.RECEIVER_ID
import com.app.harho.utils.getStringValue


class FragmentWebView : BaseFragment(), View.OnClickListener {
    lateinit var binding: FragmentWebViewBinding
    var propagator_id: String = ""
    var propagator_cat_id: String = ""
    var propagator_product_id: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(layoutInflater)
        webView()
        argumentSetData()
        return binding.root


    }

    private fun argumentSetData() {
        propagator_id = arguments?.getStringValue(BUSINESS_ID)!!
        propagator_cat_id = arguments?.getStringValue(CATEGORY_ID)!!
        propagator_product_id = arguments?.getStringValue(RECEIVER_ID)!!
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webView() {
        val url =
            "https://harho.deploywork.com/view-category-detail?propagator_id=$propagator_id&propagator_product_id=$propagator_product_id&propagator_cat_id=$propagator_cat_id"
        binding.web.loadUrl(url)
        binding.web.settings.javaScriptEnabled = true
        binding.web.webViewClient = WebViewClient()
    }

    override fun onClick(p0: View?) {

    }


}