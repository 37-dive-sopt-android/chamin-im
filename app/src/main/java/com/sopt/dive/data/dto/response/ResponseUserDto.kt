package com.sopt.dive.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDto(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserData?
)

@Serializable
data class UserData(
    @SerialName("id")
    val id: Long,
    @SerialName("username")
    val username: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int,
    @SerialName("status")
    val status: String
)