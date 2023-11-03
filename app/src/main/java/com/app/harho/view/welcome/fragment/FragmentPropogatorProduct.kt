package com.app.harho.view.welcome.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.harho.R
import com.app.harho.adapter.propogatorProduct.PropogatorProductRecyclerAdapter
import com.app.harho.base.BaseFragment
import com.app.harho.base.MyApplication
import com.app.harho.databinding.FragmentPropogatorProductBinding
import com.app.harho.model.propogator.propogatorProduct.Body
import com.app.harho.utils.BUSINESS_ID
import com.app.harho.utils.CATEGORY_ID
import com.app.harho.utils.CATEGORY_NAME
import com.app.harho.utils.Status.ERROR
import com.app.harho.utils.Status.LOADING
import com.app.harho.utils.Status.SUCCESS
import com.app.harho.utils.toast
import com.app.harho.viewmodels.PropogatorViewModel
import com.app.harho.viewmodels.ViewModelFactory


class FragmentPropogatorProduct : BaseFragment(), View.OnClickListener {
    lateinit var binding: FragmentPropogatorProductBinding
    var businessId :String = ""
    var categoryName :String = ""
    val listProduct = ArrayList<Body>()
    val viewModel by viewModels<PropogatorViewModel> { ViewModelFactory(application, repository) }
    lateinit var propogatorProductRecyclerAdapter: PropogatorProductRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPropogatorProductBinding.inflate(layoutInflater)
        binding.onClick = this
        initData()
        argumentSetData()
        observer()
        return binding.root

    }

    private fun argumentSetData() {
        binding.humidity.text = categoryName
    }

    private fun initData() {
        businessId = arguments?.getString(BUSINESS_ID)!!
        viewModel.onPropogatorProduct(businessId)

    }

    private fun observer() {
        viewModel.resultPropogatorProduct.observe(viewLifecycleOwner) {
            it.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        MyApplication.hideLoader()
                        listProduct.clear()
                        listProduct.addAll(it?.data?.body!!)
                        propogatorProductRecyclerAdapter = PropogatorProductRecyclerAdapter(
                            requireContext(),
                            listProduct,
                            callBack
                        )
                        binding.rvPropogatorProduct.adapter = propogatorProductRecyclerAdapter


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
        object : PropogatorProductRecyclerAdapter.PropogatorProductRecyclerAdapterCallBack {
            override fun onClickItem(categoryName: String, categoryId :String) {
                val bundle = Bundle()
                bundle.putString(CATEGORY_ID,categoryId)
                bundle.putString(CATEGORY_NAME, categoryName)
           findNavController().navigate(R.id.action_fragmentPropogatorProduct_to_fragmentPropogatorProductDetail,bundle)
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
