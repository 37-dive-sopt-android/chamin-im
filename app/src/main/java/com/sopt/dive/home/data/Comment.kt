package com.sopt.dive.home.data

import com.sopt.dive.R

data class Comment(
    val name: String,
    val img: Int,
    val comments: String,
)

val comments = listOf(
    Comment(
        name = "엄마",
        img = R.drawable.profile_image,
        comments = "화이팅!"
    ),
    Comment(
        name = "아빠",
        img = R.drawable.profile_image,
        comments = "좋은 하루~~"
    ),
    Comment(
        name = "가람",
        img = R.drawable.profile_image,
        comments = "날씨 좋네요"
    ),
    Comment(
        name = "해솔",
        img = R.drawable.profile_image,
        comments = "겨울엔 붕어빵!"
    ),
    Comment(
        name = "지원",
        img = R.drawable.profile_image,
        comments = "다들 화이팅!!"
    ),
    Comment(
        name = "크크",
        img = R.drawable.profile_image,
        comments = "크크 크롱"
    ),
    Comment(
        name = "뽀로로",
        img = R.drawable.profile_image,
        comments = "다 같이 놀자"
    ),
    Comment(
        name = "더미",
        img = R.drawable.profile_image,
        comments = "더미더미"
    ),
    Comment(
        name = "더미",
        img = R.drawable.profile_image,
        comments = "더미더미"
    ),
    Comment(
        name = "더미",
        img = R.drawable.profile_image,
        comments = "더미더미"
    ),
    Comment(
        name = "더미",
        img = R.drawable.profile_image,
        comments = "더미더미"
    ),
)