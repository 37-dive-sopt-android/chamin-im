package com.sopt.dive.data.service

import com.sopt.dive.data.dto.request.RequestLoginDto
import com.sopt.dive.data.dto.request.RequestSignUpDto
import com.sopt.dive.data.dto.response.ResponseLoginDto
import com.sopt.dive.data.dto.response.ResponseSignUpDto
import com.sopt.dive.data.dto.response.ResponseUserDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("/api/v1/users")
    fun signUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto>

    @POST("/api/v1/auth/login")
    fun login(
        @Body request: RequestLoginDto
    ): Call<ResponseLoginDto>

    @GET("/api/v1/users/{id}")
    fun getUserInfo(
        @Path("id") userId: Long
    ): Call<ResponseUserDto>
}