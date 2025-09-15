package org.itsolutions.mydivelog.view.screens.certifications.newCertification.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
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
        val datePickerState = rememberDatePickerState(
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis <= System.currentTimeMillis()
                }

                override fun isSelectableYear(year: Int): Boolean {
                    return year <= LocalDate.now().year
                }
            }
        )
        var selectedDate by rememberSaveable { mutableStateOf<LocalDate?>(null) }
        var showDatePicker by remember { mutableStateOf(false) }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        val millis = datePickerState.selectedDateMillis
                        millis?.let {
                            val localDate = Instant.ofEpochMilli(it)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            selectedDate = localDate
                        }
                        showDatePicker = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(datePickerState)
            }
        }


        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .imePadding()
        ) {
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
            VerticalSpacer(AppSpacing.xs)

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = selectedDate?.format(
                        DateTimeFormatter.ofLocalizedDate(
                            FormatStyle.LONG
                        ).withLocale(Locale.UK)
                    ) ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Date") },
                    trailingIcon = {
                        Icon(Icons.Default.DateRange, contentDescription = "Pick a date")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Transparent)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            showDatePicker = true
                        }
                )
            }

            WeightedSpacer()
            VerticalSpacer(AppSpacing.sm)

            DiveLogPrimaryButton(
                text = "Continue",
                enabled = issuerName.isNotEmpty() && issuerId.isNotEmpty(),
            ) {
                onConfirm(issuerName, issuerId, LocalDate.now())
            }
        }
    }
}