package com.shrutipandit.qmaker.uiFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.shrutipandit.qmaker.databinding.FragmentQuestionDetailsBinding

class QuestionDetailsFragment : Fragment() {
    private var _binding: FragmentQuestionDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private val questionsList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = arguments?.let {
            QuestionDetailsFragmentArgs.fromBundle(it).question
        }

        question?.let {
            questionsList.add(it)
        }


        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, questionsList)
        binding.listViewQuestions.adapter = arrayAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}

