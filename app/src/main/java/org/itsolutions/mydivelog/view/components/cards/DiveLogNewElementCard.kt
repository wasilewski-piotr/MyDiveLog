package org.itsolutions.mydivelog.view.components.cards

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DiveLogNewElementCard(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    onClick: () -> Unit
) {
    DiveLogButtonCard(
        modifier = modifier,
        text = text,
        onClick = onClick,
        containerColor = containerColor
    ) { tint, modifier ->
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = tint,
            modifier = modifier
        )
    }
}