package com.app.harho.view.welcome

import android.graphics.Color
import android.graphics.Color.rgb
import android.graphics.Typeface
import android.graphics.fonts.FontFamily
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.app.harho.R
import com.app.harho.base.MyApplication
import com.app.harho.databinding.LayoutChecklistBottomBinding
import com.app.harho.utils.getColors
import com.app.harho.utils.spannableBold
import com.app.harho.utils.spannableRegular
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ChecklistDialog : BottomSheetDialogFragment() {
    lateinit var binding: LayoutChecklistBottomBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = LayoutChecklistBottomBinding.inflate(layoutInflater)
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
        spanEmail()
        spanHours()


    }


    private fun spanHours() {
        val spannable = "You can expect a response \n within 48 hours"
        val spannableText = SpannableString(spannable)
        spannableText.spannableBold(34, 43)
        binding.tvHours.text = spannableText
    }


    private fun spanEmail() {
        val spannable =
            SpannableString("Please e-mail INFO@HARHO.CO.UK\n with your unique reference number\n stating which item you are missing\n")
        val spannableText = SpannableString(spannable)
        spannableText.spannableRegular(14, 31)
        binding.tvEmail.text = spannableText
    }
}