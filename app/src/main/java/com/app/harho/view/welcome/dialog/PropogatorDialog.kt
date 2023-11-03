package com.app.harho.view.welcome.dialog

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.harho.R
import com.app.harho.databinding.LayoutPropogatorDialogBinding
import com.app.harho.network.Repository
import com.app.harho.utils.spannableBoldColor
import com.app.harho.view.MainActivity
import com.app.harho.viewmodels.UpdateViewModel
import com.app.harho.viewmodels.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PropogatorDialog : BottomSheetDialogFragment(), View.OnClickListener {
    lateinit var binding: LayoutPropogatorDialogBinding
    val viewModel by viewModels<UpdateViewModel> {
        ViewModelFactory(
            application = Application(),
            repository = Repository()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = LayoutPropogatorDialogBinding.inflate(layoutInflater)
        binding.onClick = this
        spanText()
        return binding.root

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = true
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = true
            state = BottomSheetBehavior.STATE_EXPANDED

        }
    }

    private fun spanText() {
        val spannableString =
            SpannableString("Your installation is now \ncomplete, welcome to\n ‘My Harho’")
        spannableString.spannableBoldColor(46, 58)
        binding.theSoilIs.text = spannableString


    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.btnNext -> {
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }
    }
}