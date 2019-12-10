package com.example.mvvm.ui.main.repo

import retrofit2.Response
import retrofit2.http.GET

interface Mocky {
    @GET("/v2/5def28522f00005d008e0816")
    suspend fun get() : Response<ResultResponse>
}