package org.itsolutions.mydivelog.view.components.cards

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import org.itsolutions.mydivelog.R

@Composable
fun DiveLogStatisticsCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    DiveLogButtonCard(
        modifier = modifier,
        text = R.string.full_dive_report,
        onClick = onClick,
    ) { modifier ->
        Icon(
            painter = painterResource(R.drawable.bar_chart),
            contentDescription = null,
            modifier = modifier
        )
    }
}