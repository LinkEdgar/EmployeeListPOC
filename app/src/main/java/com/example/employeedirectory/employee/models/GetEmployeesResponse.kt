package com.example.employeedirectory.employee.models

data class GetEmployeesResponse(
    val employees: List<Employee> = ArrayList()
)