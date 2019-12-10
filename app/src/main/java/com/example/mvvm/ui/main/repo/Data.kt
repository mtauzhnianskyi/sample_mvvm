package com.example.mvvm.ui.main.repo

import com.squareup.moshi.Json

data class Data(@field:Json(name = "src") val src: String = "")

data class ResultResponse(@field:Json(name = "results") val results: List<Data>)