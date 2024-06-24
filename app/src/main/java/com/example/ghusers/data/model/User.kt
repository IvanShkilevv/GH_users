package com.example.ghusers.data.model

import com.google.gson.annotations.SerializedName

class User(
    val id: String,
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,

    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("company")
    val organization: String?,
    @SerializedName("following")
    val followingCount: String?,
    @SerializedName("followers")
    val followersQuantity: String?,
    @SerializedName("created_at")
    val creationDate: String?
) {
    constructor(id: String, login: String, avatarUrl: String) : this(
        id,
        login,
        avatarUrl,
        null,
        null,
        null,
        null,
        null,
        null
    )

}