package org.itsolutions.mydivelog.view.screens.certificates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.presentation.certificates.AllCertificatesViewModel
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.utils.modifier.windowHorizontalPadding
import org.itsolutions.mydivelog.view.components.DiveLogCertificationCard
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCertificatesListScreen(
    onBackPressed: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val viewModel: AllCertificatesViewModel = hiltViewModel()
    val certificates = viewModel.certifications.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_certificates),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                navigationIcon = {
                    IconButton(
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        },
                        onClick = onBackPressed
                    )
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .windowHorizontalPadding()
                .verticalScroll(scrollState)
        ) {
            DiveLogTitleWithSubtitle(
                title = R.string.certificates_held_title,
                subtitle = R.string.certificates_held_subtitle
            )
            VerticalSpacer(AppSpacing.sm)
            certificates.value.forEach {
                DiveLogCertificationCard(it)
                VerticalSpacer(AppSpacing.sm)
            }
        }
    }
}