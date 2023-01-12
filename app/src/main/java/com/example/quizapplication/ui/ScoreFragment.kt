package com.example.quizapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizapplication.R
import com.example.quizapplication.Utils
import com.example.quizapplication.data.model.Theme
import com.example.quizapplication.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoreBinding.inflate(inflater,container,false)
        (requireActivity() as MainActivity).title = getString(R.string.score_title)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.scoreMessage.text = getString(R.string.final_result,Utils.readFromSharedPrefs(requireActivity()),arguments?.getInt("score"))
        binding.back.setOnClickListener { navController.navigate(R.id.action_scoreFragment_to_chooseThemeFragment) }
    }

}