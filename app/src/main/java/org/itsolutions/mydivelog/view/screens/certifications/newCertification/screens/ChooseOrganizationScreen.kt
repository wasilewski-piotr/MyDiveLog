package org.itsolutions.mydivelog.view.screens.certifications.newCertification.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.view.components.DiveLogBaseScreenTopNavigation
import org.itsolutions.mydivelog.view.components.DiveLogTitleWithSubtitle
import org.itsolutions.mydivelog.view.components.cards.DiveLogOrganizationLogoCard
import org.itsolutions.mydivelog.view.components.semantics.VerticalSpacer

@Composable
fun ChooseOrganizationScreen(
    onBack: () -> Unit,
    onSelected: (DiveOrganization) -> Unit
) {
    DiveLogBaseScreenTopNavigation(
        screenTitle = stringResource(R.string.new_certification),
        onBack = onBack,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.xl),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.xl)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                DiveLogTitleWithSubtitle(
                    title = stringResource(R.string.choose_organization_title),
                    subtitle = stringResource(R.string.choose_organization_subtitle)
                )
                VerticalSpacer(AppSpacing.sm)
            }
            items(DiveOrganization.entries) {
                DiveLogOrganizationLogoCard(
                    image = painterResource(it.logo ?: R.drawable.ic_launcher_background),
                    color = it.backgroundColor
                ) {
                    onSelected(it)
                }
            }
        }
    }
}