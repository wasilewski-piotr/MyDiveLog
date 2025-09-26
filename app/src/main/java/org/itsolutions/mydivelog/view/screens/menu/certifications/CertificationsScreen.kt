package org.itsolutions.mydivelog.view.screens.menu.certifications

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.presentation.menu.certifications.CertificationsViewModel
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.utils.activity.activityLauncherWithResult
import org.itsolutions.mydivelog.utils.modifier.windowBottomPadding
import org.itsolutions.mydivelog.view.components.DiveLogEmptyListState
import org.itsolutions.mydivelog.view.components.DiveLogSubtitle
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle
import org.itsolutions.mydivelog.view.components.cards.DiveLogNewElementCard
import org.itsolutions.mydivelog.view.components.cards.DiveLogOrganizationCardClickable
import org.itsolutions.mydivelog.view.components.cards.DiveLogSecondaryButton
import org.itsolutions.mydivelog.view.components.semantics.HorizontalSpacer
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer
import org.itsolutions.mydivelog.view.screens.certifications.allCertifications.AllCertificationsListActivity
import org.itsolutions.mydivelog.view.screens.certifications.allCertifications.AllCertificationsListScreen
import org.itsolutions.mydivelog.view.screens.certifications.newCertification.NewCertificationActivity

@Composable
fun CertificationsScreen(
    viewModel: CertificationsViewModel
) {
    val context = LocalContext.current
    val verticalScrollState = rememberScrollState()
    val organizations = viewModel.organizations.collectAsState()
    val launcher = activityLauncherWithResult {
        if (it.resultCode == Activity.RESULT_OK) {
            viewModel.getDistinctOrganizations()
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(verticalScrollState)
            .windowBottomPadding()
    ) {
        DiveLogTitleWithSubtitle(
            title = stringResource(R.string.certifications_title),
            subtitle = stringResource(R.string.certifications_subtitle)
        )
        Row {
            DiveLogNewElementCard(
                text = R.string.new_certification,
                modifier = Modifier.weight(1f),
                onClick = { launcher(NewCertificationActivity.createInstance(context)) }
            )
            if (organizations.value.isNotEmpty()) {
                HorizontalSpacer(AppSpacing.sm)
                DiveLogSecondaryButton(
                    text = R.string.see_all_certifications,
                    modifier = Modifier.weight(1f),
                    onClick = { launcher(AllCertificationsListActivity.createInstance(context)) }
                ) { modifier ->
                    Icon(
                        painter = painterResource(R.drawable.quick_reference_all),
                        contentDescription = null,
                        modifier = modifier
                    )
                }
            }
        }
        VerticalSpacer(AppSpacing.sm)
        DiveLogSubtitle(stringResource(R.string.my_organizations))
        VerticalSpacer(AppSpacing.sm)
        if (organizations.value.isEmpty()) {
            DiveLogEmptyListState(stringResource(R.string.empty_organizations_list))
        } else {
            organizations.value.forEach { organization ->
                DiveLogOrganizationCardClickable(
                    organization = organization,
                    onClick = { launcher(AllCertificationsListActivity.createInstance(context, organization)) }
                )
                VerticalSpacer(AppSpacing.xs)
            }
        }
    }
}