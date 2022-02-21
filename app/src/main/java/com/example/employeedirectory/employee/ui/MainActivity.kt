package com.example.employeedirectory.employee.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.employeedirectory.databinding.ActivityMainBinding
import com.example.employeedirectory.employee.adapters.EmployeeAdapter
import com.example.employeedirectory.employee.models.Employee
import com.example.employeedirectory.employee.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        mockData()
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
    }

    private fun mockData() {
        val employees = ArrayList<Employee>()
        for (x in 0 until 26) {
            val employee = Employee(name = "$x", uuid = "uuid$x", phoneNumber = "63704034$x" , biography = "bio")
            employees.add(employee)
        }
        adapter?.submitList(employees)
    }
}