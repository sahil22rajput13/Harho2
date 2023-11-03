package com.app.harho.view.welcome.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.app.harho.R
import com.app.harho.adapter.leaderboard.LeaderBoardAdapter
import com.app.harho.base.BaseFragment
import com.app.harho.base.GetObjects
import com.app.harho.base.MyApplication
import com.app.harho.databinding.FragmentLeaderboardBinding
import com.app.harho.model.leaderBoard.AllScoresInfo
import com.app.harho.utils.PaginationScrollListener
import com.app.harho.utils.SharedPreference
import com.app.harho.utils.Status
import com.app.harho.utils.createPlaceholderImage
import com.app.harho.utils.toast
import com.app.harho.viewmodels.UpdateViewModel
import com.app.harho.viewmodels.ViewModelFactory
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class FragmentLeaderBoard : BaseFragment(), View.OnClickListener {

    lateinit var binding: FragmentLeaderboardBinding
    lateinit var leaderBoardAdapter: LeaderBoardAdapter
    var leaderList = ArrayList<AllScoresInfo>()
    val viewModel by viewModels<UpdateViewModel> { ViewModelFactory(application, repository) }
    var isFirstTime = false
    private var offset: Int = 0
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var socket:io.socket.client.Socket?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeaderboardBinding.inflate(layoutInflater)
        binding.onClick = this
        leaderBoardApi()
        setAdapterPagination()
        observer()
        return binding.root
    }

    private fun leaderBoardApi() {
        viewModel.onLeaderBoard(offset.toString(), "10")
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observer() {
        viewModel.resultLeaderBoard.observe(viewLifecycleOwner) {
            it.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        MyApplication.hideLoader()
//                        hideProgress()
                        isLoading = false
                        try {
                            binding.userName.text = it.data?.body?.userinfo?.display_name
                            leaderList.clear()
                            leaderList.addAll(it?.data?.body?.allScoresInfo!!)
                            GetObjects.preference.putString(SharedPreference.Key.USERID,
                                it.data.body.userinfo.id.toString())
                            val adapter = LeaderBoardAdapter(leaderList,requireContext())
                            binding.rvScorelist.adapter = adapter

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                        if (!isFirstTime) {
                            try {
                                Glide.with(requireActivity())
                                    .load(
                                        it.data?.body?.userinfo?.display_name?.let { it1 ->
                                            createPlaceholderImage(
                                                it1,
                                                requireActivity()
                                            )
                                        }
                                    ).placeholder(R.drawable.logo)
                                    .into(binding.ivProfileImg)
                            } catch (e: Exception) {

                                Glide.with(requireActivity())
                                    .load(requireActivity()).placeholder(R.drawable.logo)
                                    .into(binding.ivProfileImg)

                                e.printStackTrace()
                            }

                            isFirstTime = true
                        }
                        binding.userMail.text = it.data?.body?.userinfo?.email
                        binding.scoreVal.text = it.data?.body?.userinfo?.userscores

                        if (leaderList.isEmpty()) {
                            it.data?.body?.allScoresInfo?.let { it1 -> leaderList.addAll(it1) }
                            leaderBoardAdapter.notifyDataSetChanged()
                        } else {
                            val lastPosition = leaderList.lastIndex
                            it.data?.body?.allScoresInfo?.let { it1 -> leaderList.addAll(it1) }
                            Log.d("TAG", "observer: ${leaderList.size}")
                            leaderBoardAdapter.notifyItemRangeChanged(
                                lastPosition,
                                leaderList.lastIndex
                            )
                        }
                    }

                    Status.LOADING -> {
                        MyApplication.showLoader(requireContext())
                        isLoading = true
                        binding.apply {
//                            showProgress(requireActivity())
                        }
                    }

                    Status.ERROR -> {
//                        hideProgress()
                        isLoading = false
                        requireContext().toast(resources.message.toString())
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (socket == null) {
            Log.d("TAG", "Socket: Null")
        } else {
            if (socket!!.connected()) {
                initSocketObserverForSendMessage()
            } else {
                Log.d("TAG", "onResume: not connected")
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initSocketObserverForSendMessage() {
        socket!!.on("leaderboard")
        {
            args -> val countResponse = args[0].toString()
            val gson = GsonBuilder().create()
            val list = gson.fromJson<ArrayList<AllScoresInfo>>(countResponse , object :
                TypeToken<ArrayList<AllScoresInfo>>(){}.type
            )
            viewLifecycleOwner.lifecycleScope.launch {
                leaderList.clear()
                leaderList.addAll(list)
                leaderBoardAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun setAdapterPagination() {
        binding.rvScorelist.addOnScrollListener(object : PaginationScrollListener(
            binding.rvScorelist.layoutManager as LinearLayoutManager
        ) {
            override fun isLastPage(): Boolean {
                //     binding.progress.visibility = View.GONE
                return isLastPage
            }

            override fun isLoading(): Boolean {
                //     binding.progress.visibility = View.GONE
                return isLoading
            }

            override fun loadMoreItems() {
                //     binding.progress.visibility = View.GONE
                isLoading = true
                offset += 10
                viewModel.onLeaderBoard(offset.toString(), "10")
            }
        })
    }
    override fun onClick(p0: View?) {
        when (p0) {
            binding.ivBack -> {
                findNavController().popBackStack()
            }
        }

    }

}