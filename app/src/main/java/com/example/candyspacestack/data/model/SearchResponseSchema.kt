package com.example.candyspacestack.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponseSchema(
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("items")
    val items: List<ItemSchema>,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)
