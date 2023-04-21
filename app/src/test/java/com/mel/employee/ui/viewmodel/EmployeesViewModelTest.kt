package com.mel.employee.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mel.employee.data.Employee
import com.mel.employee.data.Page
import com.mel.employee.data.Support
import com.mel.employee.repositories.PageRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner::class)
internal class EmployeesViewModelTest{
    private lateinit var employeeViewmodel:EmployeesViewModel
    private var users: ArrayList<Employee> = ArrayList<Employee>()
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mockitorule:MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        val repository : PageRepository = Mockito.mock(PageRepository::class.java)
        employeeViewmodel = EmployeesViewModel(repository)
        users.add(Employee(1,"email","fname","lname","image"))
        employeeViewmodel.curentPage.postValue(Page(1,1,1,1,users, Support("url1","text")))
        employeeViewmodel.number.postValue(1)
    }

    @Test
    fun testGetCurentPage() {
        assertEquals(1,employeeViewmodel.curentPage.value?.page)
    }

    @Test
    fun testGetNumber() {
        assertEquals(1,employeeViewmodel.number.value)

    }

    fun loadPage() {

    }

    @Test
    fun testTextPagination() {
        assertEquals("1/1",employeeViewmodel.textPagination())
    }

    @Test
    fun testGetNextPage() {
        assertEquals(1,employeeViewmodel.getNextPage())

    }
    @Test
    fun testGetPreviewPage() {
        assertEquals(1,employeeViewmodel.getPreviewPage())
    }
}