package com.example.ghusers.data.model

import com.google.gson.annotations.SerializedName

class User(
    val id: String,
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)