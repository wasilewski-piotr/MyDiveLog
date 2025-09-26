package org.itsolutions.mydivelog.presentation.certifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import org.itsolutions.mydivelog.domain.model.results.DataError
import org.itsolutions.mydivelog.domain.model.results.onError
import org.itsolutions.mydivelog.domain.model.results.onSuccess
import org.itsolutions.mydivelog.domain.repository.CertificateRepository
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class NewCertificationViewModel @Inject constructor(
    private val certificateRepository: CertificateRepository
) : ViewModel() {

    private val _newCertificationUiState =
        MutableStateFlow<NewCertificationUiState>(NewCertificationUiState.SelectOrganization)
    val newCertificationUiState: StateFlow<NewCertificationUiState> =
        _newCertificationUiState.asStateFlow()

    private fun updateState(function: (NewCertificationUiState) -> NewCertificationUiState) {
        _newCertificationUiState.update(function)
    }

    private fun updateWithOrganization(function: (DiveOrganization) -> NewCertificationUiState) {
        updateState { if (it is NewCertificationUiState.WithOrganizationSelected) function(it.organization) else it }
    }

    fun updateOrganization(organization: DiveOrganization) {
        updateState { NewCertificationUiState.OrganizationSelected(organization) }
    }

    fun updateCertificationData(name: String, number: String) {
        updateWithOrganization { NewCertificationUiState.CertificationData(name, number, it) }
    }

    fun createCertificate(
        name: String,
        id: String,
        date: LocalDate,
        state: NewCertificationUiState.CertificationData
    ) {
        val certificate = Certificate(
            certificateName = state.certificationName,
            certificateNumber = state.certificationNumber,
            organization = state.organization,
            issuedBy = name,
            issuerId = id,
            issueDate = date
        )

        viewModelScope.launch {
            certificateRepository
                .createCertificate(certificate)
                .onSuccess {
                    updateState { NewCertificationUiState.Success(certificate) }
                }
                .onError { error ->
                    updateState { NewCertificationUiState.Error(error) }
                }
        }
    }
}

sealed interface NewCertificationUiState {
    data object SelectOrganization : NewCertificationUiState

    sealed interface WithOrganizationSelected : NewCertificationUiState {
        val organization: DiveOrganization
    }

    data class OrganizationSelected(override val organization: DiveOrganization) :
        WithOrganizationSelected

    data class CertificationData(
        val certificationName: String,
        val certificationNumber: String,
        override val organization: DiveOrganization
    ) : WithOrganizationSelected

    data class Success(val certification: Certificate) : NewCertificationUiState
    data class Error(val error: DataError) : NewCertificationUiState
}