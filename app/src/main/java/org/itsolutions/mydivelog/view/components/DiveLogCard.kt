package org.itsolutions.mydivelog.view.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.itsolutions.mydivelog.R

@Composable
fun AddElementCard(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    onClick: () -> Unit
) {
    DiveLogCard(
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

@Composable
fun StatisticsCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    DiveLogCard(
        modifier = modifier,
        text = R.string.full_dive_report,
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
    ) { tint, modifier ->
        Icon(
            painter = painterResource(R.drawable.bar_chart),
            contentDescription = null,
            tint = tint,
            modifier = modifier
        )
    }
}

@Composable
fun DiveLogCard(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    icon: @Composable (tint: Color, modifier: Modifier) -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            icon(MaterialTheme.colorScheme.onSurfaceVariant, Modifier.size(32.dp))
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
