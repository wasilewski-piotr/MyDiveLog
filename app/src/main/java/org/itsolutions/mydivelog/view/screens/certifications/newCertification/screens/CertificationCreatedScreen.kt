package org.itsolutions.mydivelog.view.screens.certifications.newCertification.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.utils.modifier.windowHorizontalPadding
import org.itsolutions.mydivelog.view.components.buttons.DiveLogPrimaryButton
import org.itsolutions.mydivelog.view.components.cards.DiveLogCertificationCard
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer
import org.itsolutions.mydivelog.view.components.semantics.WeightedSpacer
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@Composable
fun SuccessScreen(certification: Certificate, onBack: () -> Unit) {
    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .windowHorizontalPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeightedSpacer(0.5f)
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success",
                modifier = Modifier.size(96.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Certification Added Successfully",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Your certification details have been saved. You can now view them in your profile.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            VerticalSpacer(AppSpacing.lg)

            DiveLogCertificationCard(
                organization = certification.organization,
                certificationName = certification.certificateName,
                certificationNumber = certification.certificateNumber,
                allowCopy = false
            ) {
                Text(
                    "Certification Data:",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Date: ${
                        certification.issueDate.format(
                            DateTimeFormatter.ofLocalizedDate(
                                FormatStyle.LONG
                            ).withLocale(Locale.UK)
                        )
                    }", style = MaterialTheme.typography.bodySmall
                )
                Text("Name: ${certification.issuedBy}", style = MaterialTheme.typography.bodySmall)
                Text("Code: ${certification.issuerId}", style = MaterialTheme.typography.bodySmall)

                VerticalSpacer(AppSpacing.xs)
            }

            WeightedSpacer()

            DiveLogPrimaryButton(
                text = "Continue",
                onClick = onBack
            )
        }
    }
}