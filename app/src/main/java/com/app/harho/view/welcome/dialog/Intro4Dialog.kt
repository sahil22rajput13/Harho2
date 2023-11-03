package com.app.harho.view.welcome.dialog

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.harho.R
import com.app.harho.base.MyApplication
import com.app.harho.databinding.LayoutIntro4BottomBinding
import com.app.harho.model.auth.update.isupdate.IsUpdatePramModel
import com.app.harho.network.Repository
import com.app.harho.utils.Status.ERROR
import com.app.harho.utils.Status.LOADING
import com.app.harho.utils.Status.SUCCESS
import com.app.harho.view.welcome.PropogatorActivity
import com.app.harho.viewmodels.UpdateViewModel
import com.app.harho.viewmodels.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Intro4Dialog : BottomSheetDialogFragment(), View.OnClickListener {
    lateinit var binding: LayoutIntro4BottomBinding
    val viewModel by viewModels<UpdateViewModel> {
        ViewModelFactory(
            application = Application(),
            repository = Repository()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = LayoutIntro4BottomBinding.inflate(layoutInflater)
        binding.onClick = this
        return binding.root

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = true
        observer()
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = true
            state = BottomSheetBehavior.STATE_EXPANDED

        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.btnNo -> {
                Dialog4Api()
            }
        }
    }

    private fun Dialog4Api() {
        val body = IsUpdatePramModel("is_updated", "1")
        viewModel.onIsUpdate(body)
    }

    private fun observer() {
        viewModel.resultIsUpdate.observe(viewLifecycleOwner) {
            it.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        MyApplication.hideLoader()
                        startActivity(Intent(requireContext(), PropogatorActivity::class.java))

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
}


