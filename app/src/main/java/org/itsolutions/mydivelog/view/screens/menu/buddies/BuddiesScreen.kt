package org.itsolutions.mydivelog.view.screens.menu.buddies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.presentation.menu.buddies.BuddiesViewModel
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle
import org.itsolutions.mydivelog.view.components.cards.DiveLogNewElementCard

@Composable
fun BuddiesScreen(
    viewModel: BuddiesViewModel
) {
    Column {
        DiveLogTitleWithSubtitle(
            title = stringResource(R.string.buddies_title),
            subtitle = stringResource(R.string.buddies_subtitle)
        )
        DiveLogNewElementCard(
            text = R.string.new_buddy,
            modifier = Modifier.fillMaxWidth()
        ) { }
    }
}