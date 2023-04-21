package com.mel.employee.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mel.employee.R
import com.mel.employee.data.Employee
import com.mel.employee.databinding.ActivityEmployeesBinding
import com.mel.employee.ui.adapters.EmployeeAdapter
import com.mel.employee.ui.viewmodel.EmployeesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmployeesActivity : AppCompatActivity() {

    private val employeesViewModel by viewModel<EmployeesViewModel>()
    private lateinit var binding: ActivityEmployeesBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employees)
        binding.lifecycleOwner = this
        binding.mainviewmodel = employeesViewModel
        initAEmployeeAdapter(ArrayList())
        employeesViewModel.loadPage(1)
        employeesViewModel.curentPage?.observe(this) {
            binding.pagenumber.text = employeesViewModel.textPagination()
            (binding.employeesRecycleview.adapter as EmployeeAdapter).employees = it.data
            (binding.employeesRecycleview.adapter as EmployeeAdapter).notifyDataSetChanged()
        }

        employeesViewModel.number.observe(this) {
            employeesViewModel.loadPage(it)
        }

        binding.next.setOnClickListener {
            val next: Int = employeesViewModel.getNextPage()
            employeesViewModel.number.postValue(next)


        }

        binding.preview.setOnClickListener {
            val prev: Int = employeesViewModel.getPreviewPage()
            employeesViewModel.number.postValue(prev)
        }


    }

    private fun initAEmployeeAdapter(employees: ArrayList<Employee>) {
        binding.employeesRecycleview.adapter = EmployeeAdapter(employees)
        binding.employeesRecycleview.layoutManager = LinearLayoutManager(this)
        binding.employeesRecycleview.setHasFixedSize(true)
    }
}