package com.app.harho.view.welcome.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.harho.R
import com.app.harho.adapter.propogatorProduct.PropogatorDetailProductRecyclerAdapter
import com.app.harho.base.BaseFragment
import com.app.harho.base.MyApplication
import com.app.harho.databinding.FragmentPropogatorProductDetailBinding
import com.app.harho.utils.CATEGORY_NAME
import com.app.harho.utils.RECEIVER_ID
import com.app.harho.utils.Status.ERROR
import com.app.harho.utils.Status.LOADING
import com.app.harho.utils.Status.SUCCESS
import com.app.harho.utils.toast
import com.app.harho.viewmodels.PropogatorViewModel
import com.app.harho.viewmodels.ViewModelFactory


class FragmentPropogatorProductDetail : BaseFragment(), View.OnClickListener {
    lateinit var binding: FragmentPropogatorProductDetailBinding
    var categoryName: String = ""
    val listProduct = ArrayList<com.app.harho.model.propogator.productDetail.Body>()
    val viewModel by viewModels<PropogatorViewModel> { ViewModelFactory(application, repository) }
    lateinit var propogatorProductDetailRecyclerAdapter: PropogatorDetailProductRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPropogatorProductDetailBinding.inflate(layoutInflater)
        binding.onClick = this
        argumentSetData()
        initData()
        observer()
        return binding.root

    }

    private fun argumentSetData() {
        binding.humidity.text = categoryName
    }

    private fun initData() {
        categoryName = arguments?.getString(CATEGORY_NAME)!!
        viewModel.onPropogatorProductDetail()

    }

    private fun observer() {
        viewModel.resultPropogatorProductDetail.observe(viewLifecycleOwner) {
            it.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        MyApplication.hideLoader()
                        listProduct.clear()
                        listProduct.addAll(it?.data?.body!!)
                        propogatorProductDetailRecyclerAdapter =
                            PropogatorDetailProductRecyclerAdapter(
                                requireContext(),
                                listProduct,
                                callBack
                            )
                        binding.rvPropogatorProduct.adapter = propogatorProductDetailRecyclerAdapter


                    }

                    LOADING -> {
                     MyApplication.showLoader(requireContext())
                    }

                    ERROR -> {

                    }
                }
            }
        }
    }

    private val callBack =
        object :
            PropogatorDetailProductRecyclerAdapter.PropogatorProductDetailRecyclerAdapterCallBack {
            override fun onClickItem(tittle: String, receiverId: String) {
                val bundle = Bundle()
                bundle.putString(CATEGORY_NAME, tittle)
                bundle.putString(RECEIVER_ID, receiverId)
                findNavController().navigate(
                    R.id.action_fragmentPropogatorProductDetail_to_fragmentWebView,
                    bundle
                )
            }

        }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.back -> {
                findNavController().popBackStack()
            }
        }
    }
}
