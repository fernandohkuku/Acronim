package com.fernando.acronym.presentation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.fernando.acronym.R
import com.fernando.acronym.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val homeAdapter = HomeAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareUI()
        setupObservers()
    }

    private fun prepareUI() = with(viewModel) {
        binding.vm = viewModel
        binding.adapter = homeAdapter
        binding.lifecycleOwner = this@MainActivity
    }

    private fun setupObservers() = with(viewModel) {
        liveError.observe(this@MainActivity) {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}