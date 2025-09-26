package org.itsolutions.mydivelog.view.screens.certifications.newCertification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.itsolutions.mydivelog.presentation.certifications.NewCertificationUiState
import org.itsolutions.mydivelog.presentation.certifications.NewCertificationViewModel
import org.itsolutions.mydivelog.view.components.errors.DiveLogFullScreenError
import org.itsolutions.mydivelog.view.components.errors.DiveLogFullScreenWarning
import org.itsolutions.mydivelog.view.screens.certifications.newCertification.screens.CertificationDataInputScreen
import org.itsolutions.mydivelog.view.screens.certifications.newCertification.screens.ChooseOrganizationScreen
import org.itsolutions.mydivelog.view.screens.certifications.newCertification.screens.IssuerDataInputScreen
import org.itsolutions.mydivelog.view.screens.certifications.newCertification.screens.SuccessScreen

@Composable
fun NewCertificationNavHost(
    viewModel: NewCertificationViewModel,
    navController: NavHostController,
    onBack: () -> Unit
) {
    val uiState = viewModel.newCertificationUiState.collectAsState()
    NavHost(navController, NewCertificationRoutes.ChooseOrganization) {
        composable<NewCertificationRoutes.ChooseOrganization> {
            ChooseOrganizationScreen(onBack) { organization ->
                viewModel.updateOrganization(organization)
                navController.navigate(NewCertificationRoutes.CertificationData)
            }
        }

        composable<NewCertificationRoutes.CertificationData> {
            when (val uiState = uiState.value) {
                is NewCertificationUiState.WithOrganizationSelected -> {
                    CertificationDataInputScreen(
                        organization = uiState.organization,
                        onBack = { navController.popBackStack() },
                        onConfirm = { name, number ->
                            viewModel.updateCertificationData(name, number)
                            navController.navigate(NewCertificationRoutes.IssuerData)
                        }
                    )
                }

                else -> {}
            }

        }

        composable<NewCertificationRoutes.IssuerData> {
            when (val uiState = uiState.value) {
                is NewCertificationUiState.CertificationData -> {
                    IssuerDataInputScreen(
                        organization = uiState.organization,
                        certificationName = uiState.certificationName,
                        certificationNumber = uiState.certificationNumber,
                        onBack = { navController.popBackStack() },
                        onConfirm = { name, id, date ->
                            viewModel.createCertificate(
                                name,
                                id,
                                date,
                                uiState
                            )
                        }
                    )
                }

                is NewCertificationUiState.Success -> {
                    SuccessScreen(uiState.certification, onBack)
                }

                is NewCertificationUiState.Error -> {
                    DiveLogFullScreenError(
                        error = uiState.error,
                        onClose = onBack
                    )
                }

                else -> {}
            }
        }
    }
}