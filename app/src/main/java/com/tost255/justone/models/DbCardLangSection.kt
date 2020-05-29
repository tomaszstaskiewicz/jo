package com.tost255.justone.models

import org.json.JSONArray

data class DbCardLangSection(
    val word: String,
    val imageUrl: String? = "",
    val definition: String? = "",
    val phrases: JSONArray = JSONArray(),
    val musicUrl: String? = ""
    )