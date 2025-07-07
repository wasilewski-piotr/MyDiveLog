package org.itsolutions.mydivelog.view.screens.certificates

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.presentation.certificates.CertificatesViewModel
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle

@Composable
fun CertificatesScreen(
    viewModel: CertificatesViewModel
) {
    Column {
        DiveLogTitleWithSubtitle(
            title = R.string.certificates_title,
            subtitle = R.string.certificates_subtitle
        )
    }
}