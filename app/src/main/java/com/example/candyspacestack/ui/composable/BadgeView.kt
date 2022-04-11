package com.example.candyspacestack.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.candyspacestack.domain.model.Badge
import com.example.candyspacestack.ui.theme.BronzeColor
import com.example.candyspacestack.ui.theme.GoldColor
import com.example.candyspacestack.ui.theme.SilverColor

@Composable
fun BadgeView(badge: Badge) {
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
        Badge(type = BadgeType.Gold, value = badge.gold)
        Badge(type = BadgeType.Silver, value = badge.silver)
        Badge(type = BadgeType.Bronze, value = badge.bronze)
    }
}

@Composable
fun Badge(type: BadgeType, value: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = Icons.Filled.Badge, contentDescription = "Main Badge", tint = type.color)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value.toString(), color = Color.Black)
    }
}

enum class BadgeType(val color: Color) {
    Gold(GoldColor),
    Bronze(BronzeColor),
    Silver(SilverColor)
}
