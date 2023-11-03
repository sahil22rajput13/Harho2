package com.app.harho.view.welcome.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.harho.R
import com.app.harho.adapter.home.HomeRecyclerAdapter
import com.app.harho.base.BaseFragment
import com.app.harho.databinding.FragmentHomeBinding
import com.app.harho.model.propogator.propogatorTittle.Propacatorinfo
import com.app.harho.utils.BUSINESS_ID
import com.app.harho.utils.CATEGORY_ID
import com.app.harho.utils.CATEGORY_NAME
import com.app.harho.utils.Status.ERROR
import com.app.harho.utils.Status.LOADING
import com.app.harho.utils.Status.SUCCESS
import com.app.harho.utils.toast
import com.app.harho.view.MainActivity
import com.app.harho.view.PropogatorRocketActivity
import com.app.harho.viewmodels.PropogatorViewModel
import com.app.harho.viewmodels.ViewModelFactory


class FragmentHome : BaseFragment(), View.OnClickListener {
    val viewModel by viewModels<PropogatorViewModel> { ViewModelFactory(application, repository) }
    private val listTittle = ArrayList<Propacatorinfo>()
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel.onPropogatorTittle()
        init()
        observer()
        binding.onClick = this

        return binding.root

    }

    private fun init(){
     binding.navHome1.setOnClickListener(this)
    }


    private fun observer() {
        viewModel.resultPropogatorTittle.observe(viewLifecycleOwner) {
            it?.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        listTittle.clear()
                        listTittle.addAll(data.data?.body?.propacatorinfo!!)
                        val homeRecyclerAdapter =
                            HomeRecyclerAdapter(requireContext(), listTittle, callback)
                        binding.rlHomeFragment.adapter = homeRecyclerAdapter


                    }

                    LOADING -> {
                        requireContext().toast("load")
                    }

                    ERROR -> {

                    }
                }
            }
        }

    }

    val callback = object : HomeRecyclerAdapter.HomeRecyclerAdapterCallBack {
        override fun onItemClick(id: String,name: String) {
            val bundle =Bundle()
            bundle.putString(BUSINESS_ID, id)
            bundle.putString(CATEGORY_NAME,name)
            (requireActivity() as MainActivity).goneIcon()
            findNavController().navigate(R.id.action_fragmentHome_to_fragmentPropogatorProduct,bundle)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visibleIcon()

    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.navHome1 ->{
                (requireActivity() as MainActivity).goneIcon()
               findNavController().navigate(R.id.action_fragmentHome_to_fragmentChecklist)
            }
            binding.navHome3 ->{
                (requireActivity() as MainActivity).goneIcon()
                findNavController().navigate(R.id.action_fragmentHome_to_fragmentLeaderBoard)
            }
            binding.navHome2 -> {
                (requireActivity() as MainActivity).goneIcon()
             findNavController().navigate(R.id.action_fragmentHome_to_fragmentHumidity)
            } binding.navHome4 -> {
                (requireActivity() as MainActivity).goneIcon()
             findNavController().navigate(R.id.action_fragmentHome_to_fragmentWatering2)
            } binding.navHome5 -> {
                (requireActivity() as MainActivity).goneIcon()
             findNavController().navigate(R.id.action_fragmentHome_to_fragmentCycle2)
            }
        }
    }


}

