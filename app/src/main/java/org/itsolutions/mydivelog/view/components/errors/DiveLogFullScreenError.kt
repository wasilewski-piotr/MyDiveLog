package org.itsolutions.mydivelog.view.components.errors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.domain.model.results.DataError
import org.itsolutions.mydivelog.domain.model.results.toMessageResource
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.view.components.DiveLogTitle
import org.itsolutions.mydivelog.view.components.navigation.DiveLogTopNavigationClose
import org.itsolutions.mydivelog.view.components.buttons.DiveLogPrimaryButton
import org.itsolutions.mydivelog.view.components.buttons.DiveLogPrimaryOutlinedButton
import org.itsolutions.mydivelog.view.components.semantics.HorizontalSpacer
import org.itsolutions.mydivelog.view.components.semantics.WeightedSpacer
import org.itsolutions.mydivelog.view.theme.errorColor
import org.itsolutions.mydivelog.view.theme.warningColor

@Composable
private fun DiveLogFullScreenContent(
    title: String,
    tint: Color,
    error: DataError,
    onClose: () -> Unit,
    button: @Composable () -> Unit
) {
    val message = error.toMessageResource()
    DiveLogTopNavigationClose(
        screenTitle = title,
        onClose = onClose,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WeightedSpacer()
            Icon(
                painter = painterResource(id = R.drawable.frame_exclamation),
                contentDescription = title,
                tint = tint,
                modifier = Modifier.size(180.dp)
            )

            DiveLogTitle("Something went wrong")
            Text(
                text = stringResource(message),
                modifier = Modifier.padding(vertical = AppSpacing.sm),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
            )

            WeightedSpacer()
            button()
        }
    }
}

@Composable
fun DiveLogFullScreenWarning(
    error: DataError,
    onClose: () -> Unit,
    onRetry: () -> Unit,
) {
    DiveLogFullScreenContent(
        title = "Warning",
        tint = warningColor,
        error = error,
        onClose = onClose
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column (modifier = Modifier.weight(1f)) {
                DiveLogPrimaryOutlinedButton("Close") { onClose() }
            }
            HorizontalSpacer(AppSpacing.sm)
            Column (modifier = Modifier.weight(1f)) {
                DiveLogPrimaryButton("Retry") { onRetry() }
            }
        }
    }
}

@Preview
@Composable
fun DiveLogFullScreenWarningPreview() {
    DiveLogFullScreenWarning(
        error = DataError.Local.ELEMENT_ALREADY_EXISTS,
        onClose = { },
        onRetry = { }
    )
}

@Composable
fun DiveLogFullScreenError(
    error: DataError,
    onClose: () -> Unit,
) {
    DiveLogFullScreenContent(
        title = "Error",
        tint = errorColor,
        error = error,
        onClose = onClose
    ) {
        DiveLogPrimaryButton("Close") { onClose() }
    }
}

@Preview
@Composable
fun DiveLogFullScreenErrorPreview() {
    DiveLogFullScreenError(
        error = DataError.Local.CERTIFICATE_ALREADY_EXISTS,
        onClose = { }
    )
}