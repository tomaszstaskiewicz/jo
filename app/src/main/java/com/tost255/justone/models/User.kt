package com.tost255.justone.models

    data class User( //toDO: standard with firebase
        val uid: String?,
        var username: String? = "",
        var email: String? = ""
    )