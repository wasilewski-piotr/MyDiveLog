package org.itsolutions.mydivelog.presentation.certifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.domain.model.results.DataError
import org.itsolutions.mydivelog.domain.model.results.onError
import org.itsolutions.mydivelog.domain.model.results.onSuccess
import org.itsolutions.mydivelog.domain.repository.CertificateRepository
import javax.inject.Inject

@HiltViewModel
class AllCertificationsViewModel @Inject constructor(
    private val certificateRepository: CertificateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init { getAllCertifications() }

    private fun loadingState(load: () -> Unit) {
        _uiState.value = UiState.Loading
        load()
    }

    fun reloadCertifications() {
        loadingState { getAllCertifications() }
    }

    fun getAllCertifications() {
        viewModelScope.launch {
            _uiState.value = UiState.Ready(certificateRepository.getAllCertifications())
        }
    }

    fun deleteCertificate(certificate: Certificate) {
        viewModelScope.launch {
            certificateRepository.deleteCertificate(certificate)
                .onSuccess { loadingState { getAllCertifications() } }
                .onError { _uiState.value = UiState.Error(it) }
        }
    }

    sealed interface UiState {
        data object Loading : UiState
        data class Ready(val certifications: List<Certificate>): UiState
        data class Error(val error: DataError): UiState
    }
}