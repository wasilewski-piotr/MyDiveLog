package org.itsolutions.mydivelog.view.screens.menu.myDives

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.presentation.menu.myDives.MyDivesViewModel
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle
import org.itsolutions.mydivelog.view.components.cards.DiveLogNewElementCard
import org.itsolutions.mydivelog.view.components.cards.DiveLogStatisticsCard
import org.itsolutions.mydivelog.view.components.semantics.HorizontalSpacer

@Composable
fun MyDivesScreen(
    viewModel: MyDivesViewModel,
    onStatisticsClick: () -> Unit
) {
    Column {
        DiveLogTitleWithSubtitle(
            title = stringResource(R.string.my_dives_title),
            subtitle = stringResource(R.string.my_dives_subtitle)
        )
        Row {
            DiveLogNewElementCard(
                text = R.string.new_dive,
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                modifier = Modifier.weight(1f)
            ) { }
            HorizontalSpacer(AppSpacing.sm)
            DiveLogStatisticsCard(
                modifier = Modifier.weight(1f),
                onClick = onStatisticsClick
            )
        }
    }
}