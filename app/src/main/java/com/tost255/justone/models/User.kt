package com.tost255.justone.models

import java.util.*

data class User(
        val uid: String?,
        var username: String? = "",
        var email: String? = "",
        var approvedRegulations: Boolean?,
        var creationDate: Date?,
        var collections: List<JOCollections>?
    )