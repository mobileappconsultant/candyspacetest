package com.example.candyspacestack.domain.mapper

import com.example.candyspacestack.data.model.BadgeCounts
import com.example.candyspacestack.data.model.ItemSchema
import com.example.candyspacestack.domain.model.Badge
import com.example.candyspacestack.domain.model.SearchUserDomain
import com.example.candyspacestack.ui.model.User
import com.example.candyspacestack.utils.DateUtils.formatMillisToDateString
import javax.inject.Inject

class SearchMapper @Inject constructor() {
    fun mapToDomain(schema: ItemSchema): SearchUserDomain {
        return SearchUserDomain(
            name = schema.displayName,
            imageUrl = schema.profileImage,
            reputation = 0,
            location = schema.location.orEmpty(),
            badge = mapToBadge(schema.badgeCounts),
            creationDate = schema.creationDate.formatMillisToDateString()
        )
    }

    private fun mapToBadge(badgeCounts: BadgeCounts): Badge = Badge(
        gold = badgeCounts.gold,
        silver = badgeCounts.silver,
        bronze = badgeCounts.bronze
    )

    fun mapToPresentation(domain: SearchUserDomain): User {
        return User(
            name = domain.name,
            imageUrl = domain.imageUrl,
            reputation = 0,
            location = domain.location.orEmpty(),
            badge = domain.badge,
            creationDate = domain.creationDate
        )
    }
}
