package com.shrutipandit.qmaker.uiFragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.shrutipandit.qmaker.R
import com.shrutipandit.qmaker.databinding.FragmentEnterQuestionBinding
import com.shrutipandit.qmaker.databinding.FragmentHomeBinding

class EnterQuestionFragment : Fragment() {
    private var _binding: FragmentEnterQuestionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSubmit.setOnClickListener {
            val question = binding.editTextQuestion.text.toString()
            if (!TextUtils.isEmpty(question)) {
                val action = EnterQuestionFragmentDirections
                    .actionEnterQuestionFragmentToQuestionDetailsFragment(question)
                findNavController().navigate(action)
            } else {
                binding.editTextQuestion.error = "Please enter a question"
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}
