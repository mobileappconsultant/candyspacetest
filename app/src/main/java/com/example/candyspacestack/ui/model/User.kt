package com.example.candyspacestack.ui.model

import com.example.candyspacestack.domain.model.Badge

data class User(
    val name: String,
    val imageUrl: String,
    val reputation: Int,
    val location: String,
    val badge: Badge,
    val creationDate: String
)
