package com.example.employeedirectory.employee.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.employeedirectory.databinding.ActivityMainBinding
import com.example.employeedirectory.employee.adapters.EmployeeAdapter
import com.example.employeedirectory.employee.models.Employee
import com.example.employeedirectory.employee.models.Resource
import com.example.employeedirectory.employee.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<MainActivityViewModel>()

    private var adapter : EmployeeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUi()
        observeEmployees()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
        binding.recycler.recycledViewPool.clear()
        _binding = null
    }

    private fun setUi() {
        adapter = EmployeeAdapter()
        binding.recycler.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadEmployees(true)
        }
    }


    private fun observeEmployees() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadEmployees()
                viewModel.observeEmployees().collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            setProgressBar(false)
                            setErrorMessage(resource.errorMessage)
                        }
                        is Resource.Loading -> { setProgressBar(true) }
                        is Resource.Success -> {
                            binding.swipeRefresh.isRefreshing = false
                            setProgressBar(false)
                            setErrorMessage(false)
                            setEmployeeList(resource.data)
                        }
                        is Resource.Uninitiated -> {}
                    }
                }
            }
        }
    }

    private fun setEmployeeList(employees : List<Employee>) {
        adapter?.submitList(employees)
    }

    private fun setProgressBar(shouldShow : Boolean) {
        binding.progressbar.visibility = if (shouldShow) View.VISIBLE else View.GONE
    }

    private fun setErrorMessage(shouldShow : Boolean) {
        binding.errorMessage.visibility = if (shouldShow) View.VISIBLE else View.GONE
    }

    private fun setErrorMessage(errorMessage : String) {
        setErrorMessage(true)
        binding.errorMessage.text = errorMessage
    }

}