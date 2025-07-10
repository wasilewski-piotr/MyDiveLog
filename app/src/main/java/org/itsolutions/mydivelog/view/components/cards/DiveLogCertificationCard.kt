package org.itsolutions.mydivelog.view.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.view.components.buttons.CopyButton
import org.itsolutions.mydivelog.view.components.semantics.HorizontalSpacer
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer


@Composable
private fun DiveLogCertificationCardContent(
    organization: DiveOrganization,
    certificationName: String,
    certificationNumber: String,
    allowCopy: Boolean = true,
    additionalContent: @Composable () -> Unit = { }
) {
    Row(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(organization.backgroundColor ?: Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    organization.logo ?: R.drawable.ic_launcher_background
                ),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }

        HorizontalSpacer(AppSpacing.sm)

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = certificationName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                stringResource(organization.longName),
                style = MaterialTheme.typography.bodySmall
            )

            VerticalSpacer(AppSpacing.sm)

            additionalContent()

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
                        text = certificationNumber,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                if (allowCopy) {
                    CopyButton(certificationNumber)
                }
            }
        }
    }
}

@Composable
fun DiveLogCertificationCardClickable(
    organization: DiveOrganization,
    certificationName: String,
    certificationNumber: String,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        DiveLogCertificationCardContent(
            organization = organization,
            certificationName = certificationName,
            certificationNumber = certificationNumber,
        )
    }
}

@Composable
fun DiveLogCertificationCard(
    organization: DiveOrganization,
    certificationName: String,
    certificationNumber: String,
    allowCopy: Boolean = true,
    additionalContent: @Composable () -> Unit = { }
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        DiveLogCertificationCardContent(
            organization = organization,
            certificationName = certificationName,
            certificationNumber = certificationNumber,
            allowCopy = allowCopy,
            additionalContent = additionalContent
        )
    }
}
