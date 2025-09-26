package org.itsolutions.mydivelog.view.screens.certifications.allCertifications

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import org.itsolutions.mydivelog.presentation.certifications.AllCertificationsViewModel
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.view.components.DiveLogCircularLoader
import org.itsolutions.mydivelog.view.components.DiveLogEmptyListState
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle
import org.itsolutions.mydivelog.view.components.navigation.DiveLogTopNavigationBackArrow
import org.itsolutions.mydivelog.view.components.alerts.DiveLogAlertDialog
import org.itsolutions.mydivelog.view.components.cards.DiveLogCertificationCardClickable
import org.itsolutions.mydivelog.view.components.errors.DiveLogFullScreenWarning
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer

@Composable
fun AllCertificationsListScreen(
    organization: DiveOrganization? = null,
    onBack: () -> Unit,
    onReload: () -> Unit,
) {
    val viewModel: AllCertificationsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val textResources = organization.getTextResourcesByOrganization()

    when (uiState) {
        AllCertificationsViewModel.UiState.Loading -> AllCertificationsLoadingScreen(
            resources = textResources,
            onBack = onBack
        )
        is AllCertificationsViewModel.UiState.Error -> DiveLogFullScreenWarning(
            error = uiState.error,
            onClose = onBack,
            onRetry = { viewModel.reloadCertifications() }
        )
        is AllCertificationsViewModel.UiState.Ready -> AllCertificationsScreen(
            certifications = uiState.certifications,
            organization = organization,
            onDeleteCertificate = { viewModel.deleteCertificate(it) },
            onBack = onBack,
            onReload = onReload,
            resources = textResources
        )
    }
}

@Composable
private fun AllCertificationsLoadingScreen(resources: CertificationsTextResources, onBack: () -> Unit) {
    AllCertificationsBaseScreen(resources.screenTitle, onBack) {
        DiveLogCircularLoader()
    }
}

@Composable
private fun AllCertificationsScreen(
    resources: CertificationsTextResources,
    certifications: List<Certificate>,
    organization: DiveOrganization? = null,
    onDeleteCertificate: (Certificate) -> Unit,
    onBack: () -> Unit,
    onReload: () -> Unit
) {

    val scrollState = rememberScrollState()
    var shouldShowDeleteCertificateDialog by remember { mutableStateOf(false) }
    var selectedCertificate by remember { mutableStateOf<Certificate?>(null) }

    val certList = organization?.let {
        certifications.filter { cert -> cert.organization == organization }
    } ?: certifications

    AllCertificationsBaseScreen(resources.screenTitle, onBack) {

        if (shouldShowDeleteCertificateDialog) {
            DiveLogAlertDialog(
                title = stringResource(R.string.delete_certificate_alert_title),
                description = stringResource(R.string.delete_certificate_alert_description),
                confirmButtonText = stringResource(R.string.confirm),
                dismissButtonText = stringResource(R.string.cancel),
                onDismissDialog = { shouldShowDeleteCertificateDialog = false },
                onConfirm = {
                    selectedCertificate?.let { onDeleteCertificate(it) }
                    onReload()
                    shouldShowDeleteCertificateDialog = false
                }
            )
        }

        Column(
            modifier = Modifier.verticalScroll(scrollState).takeIf {
                certList.isNotEmpty()
            } ?: Modifier
        ) {
            DiveLogTitleWithSubtitle(
                title = resources.title,
                subtitle = resources.description
            )
            if (certList.isEmpty()) {
                DiveLogEmptyListState(stringResource(R.string.empty_certifications_list))
            } else {
                VerticalSpacer(AppSpacing.sm)

                certList.sortedByDescending { it.issueDate }.forEach {
                    DiveLogCertificationCardClickable(
                        organization = it.organization,
                        certificationName = it.certificateName,
                        certificationNumber = it.certificateNumber,
                        onClick = { },
                        onLongClick = {
                            selectedCertificate = it
                            shouldShowDeleteCertificateDialog = true
                        }
                    )
                    VerticalSpacer(AppSpacing.sm)
                }
            }
        }
    }
}

@Composable
private fun AllCertificationsBaseScreen(
    title: String,
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    DiveLogTopNavigationBackArrow(
        screenTitle = title,
        onBack = onBack
    ) {
        content()
    }
}

private data class CertificationsTextResources(
    val title: String,
    val description: String,
    val screenTitle: String
)

@Composable
private fun DiveOrganization?.getTextResourcesByOrganization(): CertificationsTextResources {
    return when (this) {
        null -> CertificationsTextResources(
            title = stringResource(R.string.certifications_held_title),
            description = stringResource(R.string.certifications_held_subtitle),
            screenTitle = stringResource(R.string.see_all_certifications)
        )
        else -> CertificationsTextResources(
            title = stringResource(R.string.certifications_held_by_organization_title, this.name),
            description = stringResource(R.string.certifications_held_by_organization_subtitle, this.name),
            screenTitle = stringResource(R.string.see_certifications_by_organization, this.name),
        )
    }
}