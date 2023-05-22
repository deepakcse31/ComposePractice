package com.example.testing.Repository

import com.example.testing.data.apicall.SafeApiCall
import com.example.testing.data.apicall.UserApi
import com.example.testing.util.Constants

import com.google.gson.JsonObject
import javax.inject.Inject


class loginRepository @Inject constructor(private val api: UserApi): SafeApiCall {

    suspend fun getUser(num: JsonObject) = safeApiCall {
        api.login(Constants.INTIAL_TOKEN , num)
    }
}