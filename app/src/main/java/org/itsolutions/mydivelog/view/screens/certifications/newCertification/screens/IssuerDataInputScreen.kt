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
import org.itsolutions.mydivelog.view.components.cards.DiveLogCertificationCard
import org.itsolutions.mydivelog.view.components.inputs.DiveLogTextInput
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer
import org.itsolutions.mydivelog.view.components.semantics.WeightedSpacer
import java.time.LocalDate

@Composable
fun IssuerDataInputScreen(
    organization: DiveOrganization,
    certificationName: String,
    certificationNumber: String,
    onBack: () -> Unit,
    onConfirm: (name: String, id: String, date: LocalDate) -> Unit
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
            var issuerName by rememberSaveable { mutableStateOf("") }
            var issuerId by rememberSaveable { mutableStateOf("") }

            DiveLogTitleWithSubtitle(
                title = stringResource(R.string.issuer_data_input_title),
                subtitle = stringResource(R.string.issuer_data_input_subtitle)
            )
            VerticalSpacer(AppSpacing.md)
            DiveLogCertificationCard(
                organization = organization,
                certificationName = certificationName,
                certificationNumber = certificationNumber,
                allowCopy = false
            )
            VerticalSpacer(AppSpacing.md)

            DiveLogTextInput(
                inputLabel = "Issuer Name",
                errorLabel = "Cannot be empty",
                value = issuerName,
                onValueChange = { issuerName = it },
            )
            VerticalSpacer(AppSpacing.xs)
            DiveLogTextInput(
                inputLabel = "Issuer ID",
                errorLabel = "Cannot be empty",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters
                ),
                value = issuerId,
                onValueChange = { issuerId = it },
            )


            WeightedSpacer()
            VerticalSpacer(AppSpacing.sm)

            DiveLogPrimaryButton(
                text = "Continue",
                enabled = issuerName.isNotEmpty() && issuerId.isNotEmpty(),
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
            ) {
                onConfirm(issuerName, issuerId, LocalDate.now())
            }
        }
    }
}