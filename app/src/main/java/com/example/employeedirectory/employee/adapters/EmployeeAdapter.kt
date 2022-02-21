package com.example.employeedirectory.employee.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedirectory.R
import com.example.employeedirectory.databinding.EmployeeContainerBinding
import com.example.employeedirectory.employee.models.Employee

class EmployeeAdapter () : ListAdapter<Employee, EmployeeAdapter.EmployeeViewHolder>(EmployeeDiffCallback()) {

    class EmployeeDiffCallback : DiffUtil.ItemCallback<Employee> () {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }

    }

    class EmployeeViewHolder(private val binding : EmployeeContainerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(employee : Employee) {
            binding.employee = employee
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding: EmployeeContainerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.employee_container,parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = getItem(position)
        holder.bind(employee)
    }
}