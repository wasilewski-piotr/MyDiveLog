package org.itsolutions.mydivelog.utils.activity

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun activityLauncherWithResult(onResult: (ActivityResult) -> Unit): (Intent) -> Unit {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = onResult
    )

    return { launcher.launch(it) }
}

@Composable
fun activityLauncher() = activityLauncherWithResult { /* Do nothing */ }