package com.example.employeedirectory.employee.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.di.DispatcherModule
import com.example.employeedirectory.employee.repositories.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val employeeRepo : EmployeeRepository,
    @DispatcherModule.IODispatcher
    private val ioDispatcher : CoroutineDispatcher
) : ViewModel() {

    fun observeEmployees() = employeeRepo.employeesFlow()

    fun loadEmployees() {
        viewModelScope.launch(ioDispatcher) { employeeRepo.getEmployees() }
    }
}