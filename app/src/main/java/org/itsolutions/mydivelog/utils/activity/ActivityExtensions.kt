package org.itsolutions.mydivelog.utils.activity

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import org.itsolutions.mydivelog.view.theme.MyDiveLogTheme

fun ComponentActivity.setDiveLogTheme(content: @Composable () -> Unit) {
    setContent { MyDiveLogTheme(content = content) }
}