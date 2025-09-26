package org.itsolutions.mydivelog.view.screens.certifications.allCertifications

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
    onBack: () -> Unit,
    onReload: () -> Unit,
) {
    val viewModel: AllCertificationsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        AllCertificationsViewModel.UiState.Loading -> AllCertificationsLoadingScreen(onBack)
        is AllCertificationsViewModel.UiState.Error -> DiveLogFullScreenWarning(
            error = uiState.error,
            onClose = onBack,
            onRetry = { viewModel.reloadCertifications() }
        )
        is AllCertificationsViewModel.UiState.Ready -> AllCertificationsScreen(
            certifications = uiState.certifications,
            onDeleteCertificate = { viewModel.deleteCertificate(it) },
            onBack = onBack,
            onReload = onReload
        )
    }
}

@Composable
private fun AllCertificationsLoadingScreen(onBack: () -> Unit) {
    AllCertificationsBaseScreen(onBack) {
        DiveLogCircularLoader()
    }
}

@Composable
private fun AllCertificationsScreen(
    certifications: List<Certificate>,
    onDeleteCertificate: (Certificate) -> Unit,
    onBack: () -> Unit,
    onReload: () -> Unit
) {

    val scrollState = rememberScrollState()
    var shouldShowDeleteCertificateDialog by remember { mutableStateOf(false) }
    var selectedCertificate by remember { mutableStateOf<Certificate?>(null) }

    AllCertificationsBaseScreen(onBack) {

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
                certifications.isNotEmpty()
            } ?: Modifier
        ) {
            DiveLogTitleWithSubtitle(
                title = stringResource(R.string.certifications_held_title),
                subtitle = stringResource(R.string.certifications_held_subtitle)
            )
            if (certifications.isEmpty()) {
                DiveLogEmptyListState(stringResource(R.string.empty_certifications_list))
            } else {
                VerticalSpacer(AppSpacing.sm)
                certifications.sortedBy { it.issueDate }.forEach {
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
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    DiveLogTopNavigationBackArrow(
        screenTitle = stringResource(R.string.see_all_certifications),
        onBack = onBack
    ) {
        content()
    }
}