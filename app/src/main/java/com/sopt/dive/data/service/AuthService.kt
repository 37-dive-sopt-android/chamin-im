package com.sopt.dive.data.service

import com.sopt.dive.data.dto.request.RequestLoginDto
import com.sopt.dive.data.dto.request.RequestSignUpDto
import com.sopt.dive.data.dto.response.ResponseLoginDto
import com.sopt.dive.data.dto.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/users")
    fun signUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto>
}