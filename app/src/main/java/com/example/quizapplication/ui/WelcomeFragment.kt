package com.example.quizapplication.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizapplication.R
import com.example.quizapplication.Utils
import com.example.quizapplication.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        (requireActivity() as MainActivity).title = getString(R.string.welcome_title)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.startButton.setOnClickListener {
            if (binding.name.text.toString().isEmpty())
                Toast.makeText(context,getString(R.string.set_name_error),Toast.LENGTH_SHORT).show()
            else {
                Utils.saveAtSharedPrefs(requireActivity(),binding.name.text.toString())
                navController.navigate(R.id.action_welcomeFragment_to_chooseThemeFragment)
            }
        }
    }

}