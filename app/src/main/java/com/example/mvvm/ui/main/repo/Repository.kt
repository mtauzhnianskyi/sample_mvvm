package com.example.mvvm.ui.main.repo

import com.squareup.moshi.Json
import retrofit2.Response

interface Repository {

    suspend fun getData(): Response<ResultResponse>
}

