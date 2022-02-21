package com.example.employeedirectory.networking

import com.example.employeedirectory.Constants.BASE_URL
import com.example.employeedirectory.Constants.EMPLOYEE_URL
import com.example.employeedirectory.employee.models.GetEmployeesResponse
import retrofit2.http.GET

interface EmployeeService {
    @GET(EMPLOYEE_URL)
    suspend fun getEmployeeList() : GetEmployeesResponse
}