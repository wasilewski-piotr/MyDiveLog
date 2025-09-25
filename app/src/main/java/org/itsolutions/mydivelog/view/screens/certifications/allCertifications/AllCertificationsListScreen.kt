package org.itsolutions.mydivelog.view.screens.certifications.allCertifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import org.itsolutions.mydivelog.view.components.DiveLogBaseScreenTopNavigation
import org.itsolutions.mydivelog.view.components.DiveLogEmptyListState
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle
import org.itsolutions.mydivelog.view.components.alerts.DiveLogAlertDialog
import org.itsolutions.mydivelog.view.components.cards.DiveLogCertificationCardClickable
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCertificationsListScreen(
    reload: () -> Unit,
    onBack: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val viewModel: AllCertificationsViewModel = hiltViewModel()
    val certifications = viewModel.certifications.collectAsState()

    var shouldShowDeleteCertificateDialog by remember { mutableStateOf(false) }
    var selectedCertificate by remember { mutableStateOf<Certificate?>(null) }

    if (shouldShowDeleteCertificateDialog) {
        DiveLogAlertDialog(
            title = "Dialog",
            description = "Description",
            confirmButtonText = "Confirm",
            dismissButtonText = "Dismiss",
            onDismissDialog = { shouldShowDeleteCertificateDialog = false },
            onConfirm = {
                selectedCertificate?.let { viewModel.deleteCertificate(it) }
                reload()
                shouldShowDeleteCertificateDialog = false
            }
        )
    }

    DiveLogBaseScreenTopNavigation(
        screenTitle = stringResource(R.string.see_all_certifications),
        onBack = onBack
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState).takeIf {
                certifications.value.isNotEmpty()
            } ?: Modifier
        ) {
            DiveLogTitleWithSubtitle(
                title = stringResource(R.string.certifications_held_title),
                subtitle = stringResource(R.string.certifications_held_subtitle)
            )
            if (certifications.value.isEmpty()) {
                DiveLogEmptyListState(stringResource(R.string.empty_certifications_list))
            } else {
                VerticalSpacer(AppSpacing.sm)
                certifications.value.sortedBy { it.issueDate }.forEach {
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