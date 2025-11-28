package com.sopt.dive.data

import com.sopt.dive.data.service.AuthService

object ServicePool {
    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }
}