package org.itsolutions.mydivelog.view.components.alerts

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DiveLogAlertDialog(
    title: String,
    description: String,
    confirmButtonText: String,
    dismissButtonText: String,
    onDismissDialog: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissDialog,
        title = { Text(title) },
        text = { Text(description) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissDialog) {
                Text(dismissButtonText)
            }
        }
    )
}