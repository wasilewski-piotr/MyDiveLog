package org.itsolutions.mydivelog.view.components.cards

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

private enum class ButtonCardRole {
    PRIMARY,
    SECONDARY,
    TERTIARY
}

@Composable
fun DiveLogPrimaryButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable (modifier: Modifier) -> Unit,
) {
    DiveLogButtonCard(
        text = text,
        onClick = onClick,
        modifier = modifier,
        icon = icon
    )
}

@Composable
fun DiveLogSecondaryButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable (modifier: Modifier) -> Unit,
) {
    DiveLogButtonCard(
        text = text,
        onClick = onClick,
        modifier = modifier,
        icon = icon,
        role = ButtonCardRole.SECONDARY
    )
}

@Composable
fun DiveLogTertiaryButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable (modifier: Modifier) -> Unit,
) {
    DiveLogButtonCard(
        text = text,
        onClick = onClick,
        modifier = modifier,
        icon = icon,
        role = ButtonCardRole.TERTIARY
    )
}

@Composable
private fun DiveLogButtonCard(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    role: ButtonCardRole = ButtonCardRole.PRIMARY,
    icon: @Composable (modifier: Modifier) -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (role) {
                ButtonCardRole.PRIMARY -> MaterialTheme.colorScheme.surfaceContainerHigh
                ButtonCardRole.SECONDARY -> MaterialTheme.colorScheme.surfaceContainerLow
                ButtonCardRole.TERTIARY -> MaterialTheme.colorScheme.surfaceContainerLowest
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            icon(Modifier.size(32.dp))
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
