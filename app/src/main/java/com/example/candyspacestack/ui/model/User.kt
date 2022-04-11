package com.example.candyspacestack.ui.model

import android.os.Parcelable
import com.example.candyspacestack.domain.model.Badge
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val imageUrl: String,
    val reputation: Int,
    val location: String,
    val badge: Badge,
    val creationDate: String
) : Parcelable
