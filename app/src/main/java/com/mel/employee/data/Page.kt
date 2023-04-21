package com.mel.employee.data

data class Page(
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: ArrayList<Employee> = arrayListOf(),
    var support: Support = Support()
) {

}
