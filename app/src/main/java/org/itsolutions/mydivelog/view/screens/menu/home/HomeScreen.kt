package org.itsolutions.mydivelog.view.screens.menu.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.itsolutions.mydivelog.presentation.menu.home.HomeViewModel
import org.itsolutions.mydivelog.utils.modifier.windowBottomPadding
import org.itsolutions.mydivelog.view.components.DiveLogTitle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    Column(
        modifier = Modifier.windowBottomPadding()
    ) {
        DiveLogTitle(
            title = "Home"
        )
    }
}