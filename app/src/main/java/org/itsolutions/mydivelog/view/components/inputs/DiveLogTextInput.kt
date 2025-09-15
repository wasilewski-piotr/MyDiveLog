package org.itsolutions.mydivelog.view.components.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun DiveLogTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    inputLabel: String? = null,
    errorLabel: String? = null,
    readOnly: Boolean = false,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onClick: () -> Unit = { }
) {
    var isError by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        isError = text.isEmpty()
    }

    OutlinedTextField(
        value = value,
        onValueChange = {
            validate(it)
            onValueChange(it)
        },
        label = inputLabel?.let {{ Text(it) }},
        isError = isError,
        modifier = Modifier.fillMaxSize().clickable { onClick() },
        keyboardOptions = keyboardOptions,
        readOnly = readOnly,
        trailingIcon = trailingIcon,
        supportingText = {
            errorLabel?.let {
                if (isError) {
                    Text(
                        text = errorLabel,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    )
}