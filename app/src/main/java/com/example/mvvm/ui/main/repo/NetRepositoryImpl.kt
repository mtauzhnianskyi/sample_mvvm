package com.example.mvvm.ui.main.repo

import retrofit2.Response

class NetRepositoryImpl(private val mocky: Mocky) : Repository {

    override suspend fun getData(): Response<ResultResponse> {
        return mocky.get()
    }
}