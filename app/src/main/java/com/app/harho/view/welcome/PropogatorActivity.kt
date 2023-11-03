package com.app.harho.view.welcome

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.app.harho.R
import com.app.harho.adapter.propogator.PropogatorNumberAdapter
import com.app.harho.adapter.propogator.PropogatorTittleAdapter
import com.app.harho.adapter.propogator.PropogatorViewpager
import com.app.harho.base.BaseActivity
import com.app.harho.base.MyApplication
import com.app.harho.databinding.ActivityPropogatorBinding
import com.app.harho.databinding.LayoutPropogatorDialogBinding
import com.app.harho.model.propogator.propogatorNumber.Body
import com.app.harho.model.propogator.propogatorTittle.Propacatorinfo
import com.app.harho.utils.Status.ERROR
import com.app.harho.utils.Status.LOADING
import com.app.harho.utils.Status.SUCCESS
import com.app.harho.utils.toast
import com.app.harho.view.welcome.dialog.PropogatorDialog
import com.app.harho.viewmodels.PropogatorViewModel
import com.app.harho.viewmodels.ViewModelFactory

class PropogatorActivity : BaseActivity(), View.OnClickListener {
    private lateinit var propogatorTittleAdapter: PropogatorTittleAdapter
    private lateinit var binding: ActivityPropogatorBinding
    private lateinit var bindingBottom: LayoutPropogatorDialogBinding
    private var isDialogShow = false
    private val viewModel by viewModels<PropogatorViewModel> {
        ViewModelFactory(application, repository)
    }
    private val listTittle = ArrayList<Propacatorinfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropogatorBinding.inflate(layoutInflater)
        binding.onClick = this
        setContentView(binding.root)

        propogatorApi()
        observer()
    }

    private fun propogatorApi() {
        viewModel.onPropogatorTittle()
        viewModel.onPropogatorNumber("1")
    }

    private fun observer() {
        viewModel.resultPropogatorTittle.observe(this) {
            it?.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        MyApplication.hideLoader()
                        listTittle.addAll(data.data?.body?.propacatorinfo ?: emptyList())
                        propogatorTittleAdapter = PropogatorTittleAdapter(this, listTittle)
                        binding.rvPropogatorTittle.adapter = propogatorTittleAdapter
                    }

                    LOADING -> {
                        MyApplication.showLoader(this)
                    }

                    ERROR -> {
                        toast("error")
                    }
                }
            }
        }
        viewModel.resultPropogatorNumber.observe(this) { data ->
            when (data.status) {
                SUCCESS -> {
                    MyApplication.hideLoader()
                    val listNumber = data.data?.body as? ArrayList<Body> ?: ArrayList()
                    val propogatorNumberAdapter =
                        PropogatorNumberAdapter(this@PropogatorActivity, listNumber)
                    val propogatorViewpagerAdapter =
                        PropogatorViewpager(this@PropogatorActivity, listNumber)
                    binding.rvPropogatorNumber.adapter = propogatorNumberAdapter
                    binding.vpPropogator.adapter = propogatorViewpagerAdapter

                    binding.vpPropogator.registerOnPageChangeCallback(object :
                        ViewPager2.OnPageChangeCallback() {
                        @SuppressLint("NotifyDataSetChanged")
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                            propogatorNumberAdapter.selectedPosition = position
                            propogatorNumberAdapter.notifyDataSetChanged()
                            binding.rvPropogatorNumber.smoothScrollToPosition(position)

                            if (propogatorTittleAdapter.selectedPosition == 2) {
                                if (propogatorNumberAdapter.selectedPosition == 5) {
                                    if (!isDialogShow) {
                                        val dialog = PropogatorDialog()
                                        bindingBottom =
                                            LayoutPropogatorDialogBinding.inflate(layoutInflater)
                                        dialog.show(supportFragmentManager, "")
                                        isDialogShow = true
                                    }


                                }


                            }
                            if (propogatorTittleAdapter.selectedPosition == 1) {
                                if (propogatorNumberAdapter.selectedPosition == 2) {
                                    val month = binding.vpPropogator[0]
                                    val monthText =
                                        month.findViewById<TextView>(R.id.select_date)
                                    if (TextUtils.isEmpty(monthText.text)) {
                                        toast("Require Date")
                                        viewModel.onPropogatorDate("2", monthText.toString())
                                    }
                                }


                            }
                            if (propogatorTittleAdapter.selectedPosition == 2) {
                                if (propogatorNumberAdapter.selectedPosition == 2) {
                                    val month = binding.vpPropogator[0]
                                    val monthText = month.findViewById<TextView>(R.id.select_date)
                                    viewModel.onPropogatorDate("3", monthText.toString())
                                }


                            }
                        }
                    })
                }

                LOADING -> {
                    MyApplication.showLoader(this)
                }

                ERROR -> {
                    toast("error")
                }
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(p0: View?) {
        when (p0) {
            binding.back -> {
                val lastIndex = binding.vpPropogator.adapter?.itemCount
                val currentPosition = binding.vpPropogator.currentItem
                val newPosition = currentPosition - 1
                val propogatorTittleAdapter =
                    binding.rvPropogatorTittle.adapter as PropogatorTittleAdapter
                if (binding.vpPropogator.currentItem in 0 until lastIndex!!) {
                    binding.vpPropogator.setCurrentItem(newPosition, true)
                }
                if (newPosition == -1) {
                    propogatorTittleAdapter.selectedPosition--
                    propogatorTittleAdapter.notifyDataSetChanged()
                    viewModel.onPropogatorNumber(listTittle[propogatorTittleAdapter.selectedPosition].id.toString())
                    binding.rvPropogatorTittle.adapter = propogatorTittleAdapter
                }

            }
        }

        binding.btnNext.setOnClickListener {
            val lastIndex = binding.vpPropogator.adapter?.itemCount ?: 0
            val currentPosition = binding.vpPropogator.currentItem
            val newPosition = currentPosition + 1
            val propogatorTittleAdapter =
                binding.rvPropogatorTittle.adapter as PropogatorTittleAdapter
            val propogatorNumberAdapter =
                binding.rvPropogatorNumber.adapter as PropogatorNumberAdapter
            val month = binding.vpPropogator[0]
            val monthText = month.findViewById<TextView>(R.id.select_date)

            if (currentPosition in 0 until lastIndex) {
                binding.vpPropogator.setCurrentItem(newPosition, true)
                if (!TextUtils.isEmpty(monthText.text)) {
                    viewModel.onPropogatorDate("1", monthText.toString())
                }
                when (newPosition) {
                    2 -> {
                        if (TextUtils.isEmpty(monthText.text)) {
                            toast("Require Date")
                            binding.vpPropogator.setCurrentItem(1, true)
                        }
                    }
                }
            }
            if (newPosition == 6) {
                propogatorTittleAdapter.selectedPosition++
                propogatorTittleAdapter.notifyDataSetChanged()
                viewModel.onPropogatorNumber(listTittle[propogatorTittleAdapter.selectedPosition].id.toString())
                binding.rvPropogatorTittle.adapter = propogatorTittleAdapter
            }


        }
    }
}