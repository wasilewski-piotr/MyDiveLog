package org.itsolutions.mydivelog.view.screens.menu.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.presentation.menu.statistics.StatisticsViewModel
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