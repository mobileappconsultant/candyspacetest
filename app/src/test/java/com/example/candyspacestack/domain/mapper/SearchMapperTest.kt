package com.example.candyspacestack.domain.mapper

import com.example.candyspacestack.data.model.BadgeCounts
import com.example.candyspacestack.data.model.ItemSchema
import com.example.candyspacestack.domain.model.Badge
import com.example.candyspacestack.domain.model.SearchUserDomain
import com.example.candyspacestack.ui.model.User
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchMapperTest {
    private val sut = SearchMapper()

    private val mockedName = "mockedName"
    private val mockedImageUrl = "mockedImage"
    private val mockedReputation = 0
    private val mockedLocation = "Germany"
    private val mockedBadge = BadgeCounts(gold = 0, silver = 0, bronze = 0)
    private val mockedCreationDate = 1334247880L
    private val mockedId = 23

    private val mockedSearchSchema = mockk<ItemSchema>().apply {
        every { userId } returns mockedId
        every { displayName } returns mockedName
        every { profileImage } returns mockedImageUrl
        every { badgeCounts } returns mockedBadge
        every { location } returns mockedLocation
        every { reputation } returns mockedReputation
        every { creationDate } returns mockedCreationDate
    }

    private val mockedSearchDomain = SearchUserDomain(
        name = mockedName,
        imageUrl = mockedImageUrl,
        reputation = mockedReputation,
        location = mockedLocation,
        badge = Badge(0, 0, 0),
        creationDate = "2012-04-12",
        id = mockedId
    )

    private val mockedUser = User(
        name = mockedName,
        imageUrl = mockedImageUrl,
        reputation = mockedReputation,
        location = mockedLocation,
        badge = Badge(gold = 0, silver = 0, bronze = 0),
        creationDate = "2012-04-12",
        id = mockedId
    )

    @Test
    fun `test schema mapping`() {
        val actualResult = sut.mapToDomain(mockedSearchSchema)
        assertEquals(mockedSearchDomain, actualResult)
    }

    @Test
    fun `test domain mapping`() {
        val actualResult = sut.mapToPresentation(mockedSearchDomain)
        assertEquals(mockedUser, actualResult)
    }
}
