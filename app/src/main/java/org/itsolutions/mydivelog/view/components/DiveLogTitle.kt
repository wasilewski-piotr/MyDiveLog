package org.itsolutions.mydivelog.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer

@Composable
fun DiveLogTitleWithSubtitle(
    title: String,
    subtitle: String
) {
    Column {
        DiveLogTitle(title)
        DiveLogSubtitle(subtitle)
        VerticalSpacer(AppSpacing.sm)
    }
}

@Composable
fun DiveLogTitle(title: String) {
    Column {
        VerticalSpacer(AppSpacing.md)
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = AppSpacing.sm)
        )
    }
}

@Composable
fun DiveLogSubtitle(subtitle: String) {
    Text(
        text = subtitle,
        modifier = Modifier.padding(vertical = AppSpacing.sm),
    )
}