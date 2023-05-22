package com.example.testing.data.apicall
import com.example.testing.dataclass.loginresponse
import com.google.gson.JsonObject
import retrofit2.http.*


interface UserApi {

    @POST("loginAuthapi/getOtp")
    suspend fun login(@Header("Authorization") token: String, @Body user: JsonObject?): loginresponse
}