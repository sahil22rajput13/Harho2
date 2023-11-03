package com.app.harho.view.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.harho.R
import com.app.harho.base.BaseActivity
import com.app.harho.base.MyApplication
import com.app.harho.databinding.ActivityCheckListBinding
import com.app.harho.databinding.LayoutChecklistBottomBinding
import com.app.harho.model.auth.update.recieved.RecievedPramModel
import com.app.harho.utils.Status
import com.app.harho.utils.Status.*
import com.app.harho.utils.toast
import com.app.harho.viewmodels.UpdateViewModel
import com.app.harho.viewmodels.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog


class CheckListActivity : BaseActivity(), View.OnClickListener {
    val viewModel by viewModels<UpdateViewModel> {ViewModelFactory(application, repository)  }
    lateinit var binding: ActivityCheckListBinding
    private lateinit var bottomsheet_binding: LayoutChecklistBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick =this
        observer()
    }

    private fun observer() {
        viewModel.resultRecieved.observe(this){
            it.let {
                data ->when(data.status){
                SUCCESS -> {
                    MyApplication.hideLoader()
                    startActivity(Intent(this,ReadyActivity::class.java))
                }
                LOADING -> {
                   MyApplication.showLoader(this)
                }
                ERROR -> {

                }
            }
            }
        }

    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.btnYes ->{
                checkListApi()
            }
            binding.btnNo ->{
                val dialog = ChecklistDialog()
                bottomsheet_binding = LayoutChecklistBottomBinding.inflate(layoutInflater)
                dialog.show(supportFragmentManager,"")
            }
        }
    }

    private fun checkListApi() {
        val body = RecievedPramModel("received_everything","1" )
        viewModel.onRecieved(body)
    }
}