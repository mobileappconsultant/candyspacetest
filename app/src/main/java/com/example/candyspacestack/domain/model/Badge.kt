package com.example.candyspacestack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Badge(
    val gold: Int,
    val silver: Int,
    val bronze: Int
) : Parcelable
