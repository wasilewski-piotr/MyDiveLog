package org.itsolutions.mydivelog.view.components.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.itsolutions.mydivelog.utils.modifier.windowHorizontalPadding

@Composable
fun DiveLogTopNavigationBackArrow(
    screenTitle: String,
    onBack: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    DiveLogBaseScreenTopNavigation(
        screenTitle = screenTitle,
        content = content,
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun DiveLogTopNavigationClose(
    screenTitle: String,
    onClose: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    DiveLogBaseScreenTopNavigation(
        screenTitle = screenTitle,
        content = content,
        actions = {
            IconButton(onClose) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiveLogBaseScreenTopNavigation(
    screenTitle: String,
    content: @Composable ColumnScope.() -> Unit,
    navigationIcon: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = { }
) {
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = screenTitle,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    navigationIcon = navigationIcon,
                    actions = actions
                )
                HorizontalDivider(thickness = 1.dp)
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .windowHorizontalPadding(),
            content = content
        )
    }
}