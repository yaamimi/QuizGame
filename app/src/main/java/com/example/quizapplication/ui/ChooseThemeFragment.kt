package com.example.quizapplication.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizapplication.R
import com.example.quizapplication.data.model.Theme
import com.example.quizapplication.databinding.FragmentChooseThemeBinding
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.logger.Level

class ChooseThemeFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentChooseThemeBinding
    val mainViewModel: MainViewModel by viewModel()
    lateinit var results: List<Theme>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseThemeBinding.inflate(inflater,container,false)
        (requireActivity() as MainActivity).title = getString(R.string.theme_title)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        mainViewModel.getThemes().observe(viewLifecycleOwner) {
            when(it.status) {
                MainViewModel.Status.SUCCESS -> {
                    if(it.data != null) {
                        if (it.data.error == null) {
                            binding.sport.isEnabled = true
                            binding.histoire.isEnabled = true
                            results = it.data.themes
                        }
                        else {
                            getKoin().logger.log(Level.ERROR,it.message.toString())
                            Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    }
                }
                MainViewModel.Status.ERROR -> {
                    getKoin().logger.log(Level.ERROR,it.message.toString())
                    Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
                MainViewModel.Status.LOADING -> {
                    getKoin().logger.log(Level.INFO,"Loading ...")
                }
            }
        }
        binding.histoire.setOnClickListener {
            val bundle = bundleOf("theme" to results.first { it.label.lowercase() == getString(R.string.histoire).lowercase()})
            navController.navigate(R.id.action_chooseThemeFragment_to_quizFragment,bundle) }
        binding.sport.setOnClickListener {
            val bundle = bundleOf("theme" to results.first { it.label.lowercase() == getString(R.string.sport).lowercase()})
            navController.navigate(R.id.action_chooseThemeFragment_to_quizFragment,bundle) }
    }

}