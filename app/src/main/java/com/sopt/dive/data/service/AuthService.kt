package com.sopt.dive.data.service

import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.response.LoginData
import com.sopt.dive.data.dto.response.ProfileResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("/api/v1/users")
    suspend fun signUp(
        @Body request: SignUpRequestDto
    ): BaseResponse<Unit>

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): BaseResponse<LoginData>

    @GET("/api/v1/users/{id}")
    suspend fun getUserInfo(
        @Path("id") userId: Long
    ): BaseResponse<ProfileResponseDto>
}