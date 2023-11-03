package com.app.harho.view.welcome.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.harho.databinding.FragmentChecklistBinding
import com.app.harho.view.PropogatorRocketActivity
import com.app.harho.view.QuestionActivity

class FragmentChecklist : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentChecklistBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChecklistBinding.inflate(layoutInflater)
        binding.onClick = this
        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.ivBack ->{
                findNavController().popBackStack()
            }
            binding.clCheckList ->{
                startActivity(Intent(requireContext(),PropogatorRocketActivity::class.java))
            }
            binding.clQuiz ->{
                startActivity(Intent(requireContext(),QuestionActivity::class.java))
            }
        }
    }

}