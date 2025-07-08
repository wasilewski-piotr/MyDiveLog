package org.itsolutions.mydivelog.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.view.components.buttons.CopyButton
import org.itsolutions.mydivelog.view.components.semantics.HorizontalSpacer
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@Composable
fun DiveLogCertificationCard(certificate: Certificate) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(certificate.organization.backgroundColor ?: Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        certificate.organization.logo ?: R.drawable.ic_launcher_background
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }

            HorizontalSpacer(AppSpacing.sm)

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = certificate.certificateName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    stringResource(certificate.organization.longName),
                    style = MaterialTheme.typography.bodySmall
                )

                VerticalSpacer(AppSpacing.sm)

                Text(
                    "Certification Data:",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Date: ${
                        certificate.issueDate.format(
                            DateTimeFormatter.ofLocalizedDate(
                                FormatStyle.LONG
                            ).withLocale(Locale.UK)
                        )
                    }", style = MaterialTheme.typography.bodySmall
                )
                Text("Name: ${certificate.issuedBy}", style = MaterialTheme.typography.bodySmall)
                Text("Code: ${certificate.issuerId}", style = MaterialTheme.typography.bodySmall)

                VerticalSpacer(AppSpacing.xs)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Licence Number:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = certificate.certificateNumber,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    CopyButton(certificate.certificateNumber)
                }
            }
        }
    }
}
