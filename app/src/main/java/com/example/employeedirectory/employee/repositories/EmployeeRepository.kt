package com.example.employeedirectory.employee.repositories

import com.example.employeedirectory.employee.models.Employee
import com.example.employeedirectory.employee.models.Resource
import com.example.employeedirectory.networking.EmployeeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(
    private val employeeService: EmployeeService
) {

    private var _employees : MutableStateFlow<Resource<List<Employee>>> = MutableStateFlow(Resource.Uninitiated())

    fun employeesFlow() : StateFlow<Resource<List<Employee>>> = _employees

    suspend fun getEmployees(isRefresh : Boolean = false) {
        if (isRefresh || !employeesCacheAvailable()) {
            fetchEmployeesFromNetwork()
        }
    }

    private fun employeesCacheAvailable() : Boolean {
        return _employees.value is Resource.Success // already successfully fetched employee
    }

    private suspend fun fetchEmployeesFromNetwork() {
        try {
            _employees.value = Resource.Loading(emptyList())
            val response = employeeService.getEmployeeList()
            _employees.value = Resource.Success(response.employees)
        } catch (e : Exception) {
            _employees.value = Resource.Error(e.localizedMessage, emptyList())
        }
    }
}