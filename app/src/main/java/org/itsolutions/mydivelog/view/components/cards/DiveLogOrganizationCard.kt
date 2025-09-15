package org.itsolutions.mydivelog.view.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import org.itsolutions.mydivelog.utils.AppSpacing

@Composable
fun DiveLogOrganizationLogoCard(
    image: Painter,
    color: Color?,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = color ?: Color.Transparent
        ),
        modifier = Modifier.aspectRatio(1f),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppSpacing.sm)
                    .takeIf { color != null } ?: Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun DiveLogOrganizationCardContent(
    organization: DiveOrganization,
    endIcon: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .then(organization.backgroundColor?.let {
                    Modifier.background(it)
                } ?: Modifier),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(organization.logo ?: R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = organization.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(organization.longName),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        endIcon?.let { it() }
    }
}

@Composable
fun DiveLogOrganizationCardClickable(
    organization: DiveOrganization,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        DiveLogOrganizationCardContent(organization) {
            Icon(
                painter = painterResource(R.drawable.chevron_right),
                contentDescription = "Navigate",
            )
        }
    }
}

@Composable
fun DiveLogOrganizationCard(organization: DiveOrganization) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        DiveLogOrganizationCardContent(organization)
    }
}