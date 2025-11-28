package com.sopt.dive.my.data

data class UserProfile(
    val id: Long = 0,
    val username: String = "",  // Id
    val name: String = "", // nickname
    val email: String = "",
    val age: String = "",
    val description: String = "",
    val profileImageRes: Int = 0
)