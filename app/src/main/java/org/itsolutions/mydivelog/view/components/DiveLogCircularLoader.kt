package org.itsolutions.mydivelog.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.utils.modifier.windowBottomPadding
import org.itsolutions.mydivelog.utils.modifier.windowHorizontalPadding
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer

@Composable
fun DiveLogCircularLoader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowBottomPadding()
            .windowHorizontalPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Loading")
        VerticalSpacer(AppSpacing.md)
        CircularProgressIndicator(
            modifier = Modifier.size(128.dp)
        )
    }
}