package com.sopt.dive.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    @SerialName("userId")
    val userId: Long,
    @SerialName("message")
    val message: String
)