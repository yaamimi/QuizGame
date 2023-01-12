package com.example.quizapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizapplication.R
import com.example.quizapplication.Utils
import com.example.quizapplication.data.model.Theme
import com.example.quizapplication.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentScoreBinding
    private var score : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoreBinding.inflate(inflater,container,false)
        (requireActivity() as MainActivity).title = getString(R.string.score_title)
        score = arguments?.getInt("score") ?: 0
        if(score >= 10)
            binding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
        else
            binding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.scoreMessage.text = getString(R.string.final_result,Utils.readFromSharedPrefs(requireActivity()),
            score
        )
        binding.back.setOnClickListener { navController.navigate(R.id.action_scoreFragment_to_chooseThemeFragment) }
    }

}