package com.app.harho.view.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.core.view.get
import com.app.harho.base.BaseActivity
import com.app.harho.base.GetObjects
import com.app.harho.base.MyApplication
import com.app.harho.databinding.ActivitySignupBinding
import com.app.harho.model.auth.signup.SignUpPramModel
import com.app.harho.utils.SharedPreference
import com.app.harho.utils.Status
import com.app.harho.utils.Status.*
import com.app.harho.utils.gone
import com.app.harho.utils.toast
import com.app.harho.utils.visible
import com.app.harho.view.welcome.BoxArrivedActivity
import com.app.harho.viewmodels.AuthViewModel
import com.app.harho.viewmodels.ViewModelFactory
import java.sql.Time
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.regex.Pattern


class SignupActivity : BaseActivity(), View.OnClickListener {
    val viewModel by viewModels<AuthViewModel> { ViewModelFactory(application, repository) }
    private lateinit var binding: ActivitySignupBinding
    var gender: String = ""
    val mList = arrayOf<String?>(
        "Select here",
        "My Self",
        "Family",
        "Group"
    )
    val whoseGrowing: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick = this
        genderCheck()
        spinnerData()
        observer()


    }

    private fun observer() {
        viewModel.resultSignUp.observe(this) {
            it.let { data ->
                when (data.status) {
                    SUCCESS -> {
                        MyApplication.hideLoader()
                        val response = it?.data?.body
                        GetObjects.preference.putString(SharedPreference.Key.USERID,response?.id.toString())
                        GetObjects.preference.getString(SharedPreference.Key.TOKEN,response?.token.toString())
                        if (response?.token.isNullOrEmpty()){
                            toast("Check your detail")
                        }
                        else{
                            startActivity(Intent(this,BoxArrivedActivity::class.java))
                        }

                    }
                    LOADING -> {
                        MyApplication.showLoader(this)
                    }
                    ERROR -> {
                        MyApplication.hideLoader()
                        toast(data.message.toString())
                    }
                }
            }
        }
    }

private fun spinnerData() {

    val mArrayAdapter = ArrayAdapter<Any?>(this, com.app.harho.R.layout.spinner_list, mList)
    mArrayAdapter.setDropDownViewResource(com.app.harho.R.layout.spinner_list)
    binding.spGrowing.adapter = mArrayAdapter
}

private fun genderCheck() {
    binding.GenderOther.setOnCheckedChangeListener { _, b ->
        if (b) {
            binding.GenderOther.isChecked = true
            binding.GenderMale.isChecked = false
            binding.GenderFemale.isChecked = false
            binding.rlOtherGenderForm.visible()
            gender = "other"
        }
    }
    binding.GenderMale.setOnCheckedChangeListener { _, b ->
        if (b) {
            binding.GenderOther.isChecked = false
            binding.GenderMale.isChecked = true
            binding.GenderFemale.isChecked = false
            binding.rlOtherGenderForm.gone()
            gender = "Male"
        }
    }
    binding.GenderFemale.setOnCheckedChangeListener { _, b ->
        if (b) {
            binding.GenderOther.isChecked = false
            binding.GenderMale.isChecked = false
            binding.GenderFemale.isChecked = true
            binding.rlOtherGenderForm.gone()
            gender = "Female"
        }
    }
}

override fun onClick(p0: View?) {
    when (p0) {
        binding.btnSignup -> {
            if (SignUpValidate()) {
                SignUpApi()
            }
        }

    }
}

private fun SignUpValidate(): Boolean {
    var isValid = true
    if (TextUtils.isEmpty(binding.etName.text.toString())) {
        isValid = false
        binding.etName.error = "Required Field"
    }
    if (TextUtils.isEmpty(binding.etEmail.text.toString())) {
        isValid = false
        binding.etEmail.error = "Required Field"
    } else if (!Pattern.matches(
            Patterns.EMAIL_ADDRESS.toRegex().toString(), binding
                .etEmail.text.toString()
        )
    ) {
        isValid = false
        binding.etEmail.error = "Enter a valid Email"
    }
    if (TextUtils.isEmpty(binding.etDisplayName.text.toString())) {
        isValid = false
        binding.etDisplayName.error = "Required Field"
    }
    if (TextUtils.isEmpty(binding.etAge.text.toString())) {
        isValid = false
        binding.etAge.error = "Required Field"
    }
    if (TextUtils.isEmpty(binding.etProject.text.toString())) {
        isValid = false
        binding.etProject.error = "Required Field"
    }
    if (gender == "") {
        isValid = false
        toast("Select your gender")
    }
    if (!binding.checkbox.isChecked) {
        toast("Please select terms and conditions")
    }
    if (gender == "other") {
        if (TextUtils.isEmpty(binding.etOtherGender.text.toString()))
            isValid = false
        toast("Required Field")
    }



    return isValid
}

private fun SignUpApi() {
    val name = binding.etName.text.toString()
    val email = binding.etEmail.text.toString()
    val displayName = binding.etDisplayName.text.toString()
    val age = binding.etAge.text.toString()
    val projectName = binding.etProject.text.toString()
    val otherGender = binding.etOtherGender.text.toString()
    val currentDateTimeGMT = getCurrentDateTimeInGMT()
    val body = SignUpPramModel(
        age,
        displayName,
        email,
        gender,
        currentDateTimeGMT,
        name,
        otherGender,
        projectName,
        "1",
        "self"
    )
    viewModel.onSignUp(body)

}

private fun getCurrentDateTimeInGMT(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    dateFormat.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
    val indiaTimeZone = TimeZone.getTimeZone("Asia/Kolkata")
    val currentDate = Calendar.getInstance(indiaTimeZone).time
    return dateFormat.format(currentDate)
}


}
