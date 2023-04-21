package com.mel.employee.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mel.employee.corotines.Corotines
import com.mel.employee.data.Page
import com.mel.employee.repositories.PageRepository
import com.mel.employee.utils.NoInternetExceptions

class EmployeesViewModel(
    private val repository: PageRepository
) : ViewModel() {

    var curentPage: MutableLiveData<Page> = MutableLiveData<Page>()
    var number: MutableLiveData<Int> = MutableLiveData<Int>()

    fun loadPage(number: Int) {
        Corotines.main {
            try {
                val res = repository.getUsers(number)
                if (res.isSuccessful)
                    curentPage?.postValue(res.body())
            } catch (ex: NoInternetExceptions) {
                Log.i(TAG, "No Internet exception ${ex.message}")
            }

        }
    }

    fun textPagination(): String {
        curentPage.let {
            return "${it.value?.page}/${it.value?.total_pages}"
        }
    }

    fun getNextPage(): Int {
        if (curentPage.value != null) {
            val p:Page = curentPage.value!!
            return if (p.page < p.total_pages)
                ++p.page
            else
                p.page
        }
       return 1
    }

    fun getPreviewPage(): Int {
        if (curentPage.value != null) {
            val p:Page = curentPage.value!!
            return if (p.page > 1)
                --p.page
            else
                p.page
        }
       return 1
    }


}