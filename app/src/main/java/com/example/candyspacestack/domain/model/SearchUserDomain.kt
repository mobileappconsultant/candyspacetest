package com.example.candyspacestack.domain.model

data class SearchUserDomain(
    val name: String,
    val imageUrl: String,
    val reputation: Int,
    val location: String,
    val badge: Badge,
    val creationDate: String
)
