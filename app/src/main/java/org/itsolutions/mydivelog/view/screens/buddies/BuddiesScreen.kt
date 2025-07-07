package org.itsolutions.mydivelog.view.screens.buddies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.presentation.buddies.BuddiesViewModel
import org.itsolutions.mydivelog.view.components.AddElementCard
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle

@Composable
fun BuddiesScreen(
    viewModel: BuddiesViewModel
) {
    Column {
        DiveLogTitleWithSubtitle(
            title = R.string.buddies_title,
            subtitle = R.string.buddies_subtitle
        )
        AddElementCard(
            text = R.string.new_buddy,
            modifier = Modifier.fillMaxWidth()
        ) { }
    }
}