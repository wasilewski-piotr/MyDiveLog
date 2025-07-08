package org.itsolutions.mydivelog.view.screens.certificates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.presentation.certificates.CertificatesViewModel
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.utils.modifier.windowBottomPadding
import org.itsolutions.mydivelog.view.components.AddElementCard
import org.itsolutions.mydivelog.view.components.DiveLogCard
import org.itsolutions.mydivelog.view.components.DiveLogSubtitle
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle
import org.itsolutions.mydivelog.view.components.InfoCard
import org.itsolutions.mydivelog.view.components.semantics.HorizontalSpacer
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer

@Composable
fun CertificatesScreen(
    viewModel: CertificatesViewModel
) {
    val verticalScrollState = rememberScrollState()
    val organizations = viewModel.organizations.collectAsState()

    Column(modifier = Modifier
        .verticalScroll(verticalScrollState)
        .windowBottomPadding()) {
        DiveLogTitleWithSubtitle(
            title = R.string.certificates_title,
            subtitle = R.string.certificates_subtitle
        )
        Row {
            AddElementCard(
                text = R.string.new_certificate,
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                modifier = Modifier.weight(1f)
            ) {

            }
            HorizontalSpacer(AppSpacing.sm)
            DiveLogCard(
                text = R.string.see_all_certificates,
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                modifier = Modifier.weight(1f),
                onClick = { }
            ) { tint, modifier ->
                Icon(
                    painter = painterResource(R.drawable.quick_reference_all),
                    contentDescription = null,
                    tint = tint,
                    modifier = modifier
                )
            }
        }
        VerticalSpacer(AppSpacing.sm)
        DiveLogSubtitle(R.string.my_organizations)
        VerticalSpacer(AppSpacing.sm)
        organizations.value.forEach { organization ->
            InfoCard(
                image = painterResource(organization.logo ?: R.drawable.ic_launcher_background),
                modifier = organization.backgroundColor?.let {
                    Modifier.background(it)
                } ?: Modifier,
                title = organization.name,
                subtitle = stringResource(organization.longName),
                onClick = { }
            )
            VerticalSpacer(AppSpacing.xs)
        }
    }
}