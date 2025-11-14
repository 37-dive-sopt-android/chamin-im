package com.sopt.dive.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: LoginData?
)

@Serializable
data class LoginData(
    @SerialName("userId")
    val userId: Long,
    @SerialName("message")
    val message: String
)