package com.example.candyspacestack.domain.model

data class SearchUserDomain(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val reputation: Int,
    val location: String,
    val badge: Badge,
    val creationDate: String
)
