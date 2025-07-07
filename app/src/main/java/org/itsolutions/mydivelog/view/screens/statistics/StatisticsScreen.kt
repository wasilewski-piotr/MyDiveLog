package org.itsolutions.mydivelog.view.screens.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.presentation.statistics.StatisticsViewModel
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel
) {
    Column {
        DiveLogTitleWithSubtitle(
            title = R.string.statistics_title,
            subtitle = R.string.statistics_subtitle
        )
    }
}