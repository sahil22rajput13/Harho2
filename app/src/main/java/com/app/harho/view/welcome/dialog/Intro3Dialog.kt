package com.app.harho.view.welcome.dialog

import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.harho.R
import com.app.harho.databinding.ActivityIntroBinding
import com.app.harho.databinding.LayoutIntro3BottomBinding
import com.app.harho.utils.visible
import com.app.harho.view.welcome.fragment.FragmentIntro4
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Intro3Dialog() : BottomSheetDialogFragment(), View.OnClickListener {
    lateinit var binding: LayoutIntro3BottomBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = LayoutIntro3BottomBinding.inflate(layoutInflater)
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
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = true
            state = BottomSheetBehavior.STATE_EXPANDED

        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.btnNext -> {
                replaceFragment()
                // Dismiss the bottom sheet after clicking the button if desired
                dismiss()
            }
        }
    }

    private fun replaceFragment() {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        val newFragment = FragmentIntro4() // Replace YourNewFragment with the fragment you want to show
        fragmentTransaction.replace(R.id.flIntro, newFragment)
        fragmentTransaction.addToBackStack(null) // Add the transaction to the back stack if needed
        fragmentTransaction.commit()
    }
}

