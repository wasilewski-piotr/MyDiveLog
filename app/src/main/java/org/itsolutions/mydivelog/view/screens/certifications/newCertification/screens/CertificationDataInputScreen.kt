package org.itsolutions.mydivelog.view.screens.certifications.newCertification.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.view.components.DiveLogBaseScreenTopNavigation
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle
import org.itsolutions.mydivelog.view.components.buttons.DiveLogPrimaryButton
import org.itsolutions.mydivelog.view.components.cards.DiveLogOrganizationCard
import org.itsolutions.mydivelog.view.components.inputs.DiveLogTextInput
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer
import org.itsolutions.mydivelog.view.components.semantics.WeightedSpacer

@Composable
fun CertificationDataInputScreen(
    organization: DiveOrganization,
    onBack: () -> Unit,
    onConfirm: (certificationName: String, certificationNumber: String) -> Unit,
) {
    DiveLogBaseScreenTopNavigation(
        screenTitle = stringResource(R.string.new_certification),
        onBack = onBack
    ) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .imePadding()) {
            var certificationName by rememberSaveable { mutableStateOf("") }
            var certificationNumber by rememberSaveable { mutableStateOf("") }

            DiveLogTitleWithSubtitle(
                title = stringResource(R.string.certification_data_input_title),
                subtitle = stringResource(R.string.certification_data_input_subtitle)
            )
            VerticalSpacer(AppSpacing.md)
            DiveLogOrganizationCard(organization)
            VerticalSpacer(AppSpacing.md)

            DiveLogTextInput(
                inputLabel = "Certification Name",
                errorLabel = "Cannot be empty",
                value = certificationName,
                onValueChange = { certificationName = it },
            )
            VerticalSpacer(AppSpacing.xs)
            DiveLogTextInput(
                inputLabel = "Certification Number",
                errorLabel = "Cannot be empty",
                value = certificationNumber,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters
                ),
                onValueChange = { certificationNumber = it },
            )

            WeightedSpacer()
            VerticalSpacer(AppSpacing.md)

            DiveLogPrimaryButton(
                text = "Continue",
                enabled = certificationNumber.isNotEmpty() && certificationName.isNotEmpty(),
                onClick = { onConfirm(certificationName, certificationNumber) }
            )
        }
    }
}