package org.itsolutions.mydivelog.view.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer

@Composable
fun DiveLogTitleWithSubtitle(
    @StringRes title: Int,
    @StringRes subtitle: Int
) {
    Column {
        VerticalSpacer(AppSpacing.md)
        DiveLogTitle(title)
        DiveLogSubtitle(subtitle)
        VerticalSpacer(AppSpacing.sm)
    }
}

@Composable
fun DiveLogTitle(@StringRes title: Int) {
    Text(
        text = stringResource(title),
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(vertical = AppSpacing.sm)
    )
}

@Composable
fun DiveLogSubtitle(@StringRes subtitle: Int) {
    Text(
        text = stringResource(subtitle),
        modifier = Modifier.padding(vertical = AppSpacing.sm)
    )
}