package com.mel.employee.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mel.employee.R
import com.mel.employee.data.Employee
import com.mel.employee.databinding.EmployeeItemBinding

class EmployeeAdapter(var employees: ArrayList<Employee>) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeListAdapterViewHolder>() {

    inner class EmployeeListAdapterViewHolder(
        val employeeItemBinding: EmployeeItemBinding
    ) : RecyclerView.ViewHolder(employeeItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EmployeeListAdapterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.employee_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: EmployeeListAdapterViewHolder, position: Int) {
        holder.employeeItemBinding.itememployee = employees[position]
    }

    override fun getItemCount(): Int = employees.size

}