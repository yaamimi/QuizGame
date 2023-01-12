package com.example.quizapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizapplication.R
import com.example.quizapplication.data.model.Question
import com.example.quizapplication.data.model.Theme
import com.example.quizapplication.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentQuizBinding
    lateinit var results: Theme
    var iterator = 0
    var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater,container,false)
        (requireActivity() as MainActivity).title = getString(R.string.quiz_title)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        results = arguments?.get("theme") as Theme
        displayNext()
        binding.answer1.setOnClickListener {
            if(results.questions[iterator].answers[0].correct)
                score+=5
            iterator++
            displayNext()
        }
        binding.answer2.setOnClickListener {
            if(results.questions[iterator].answers[1].correct)
                score+=5
            iterator++
            displayNext()
        }
        binding.answer3.setOnClickListener {
            if(results.questions[iterator].answers[2].correct)
                score+=5
            iterator++
            displayNext()
        }

    }

    private fun displayNext() {
        if(iterator<results.questions.size) {
            val question = results.questions[iterator]
            binding.question.text = question.label
            binding.answer1.text = results.questions[iterator].answers[0].label
            binding.answer2.text = question.answers[1].label
            binding.answer3.text = question.answers[2].label
        } else {
            val bundle = bundleOf("score" to score)
            navController.navigate(R.id.action_quizFragment_to_scoreFragment,bundle)
        }
    }

}