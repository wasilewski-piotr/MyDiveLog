package org.itsolutions.mydivelog.presentation.certifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.domain.model.results.onError
import org.itsolutions.mydivelog.domain.model.results.onSuccess
import org.itsolutions.mydivelog.domain.repository.CertificateRepository
import javax.inject.Inject

@HiltViewModel
class AllCertificationsViewModel @Inject constructor(
    private val certificateRepository: CertificateRepository
) : ViewModel() {

    private val _certifications = MutableStateFlow<List<Certificate>>(emptyList())
    val certifications: StateFlow<List<Certificate>> = _certifications.asStateFlow()

    init {
        getAllCertifications()
    }

    fun getAllCertifications() {
        viewModelScope.launch {
            _certifications.value = certificateRepository.getAllCertifications()
        }
    }

    fun deleteCertificate(certificate: Certificate) {
        viewModelScope.launch {
            certificateRepository.deleteCertificate(certificate)
                .onSuccess {
                    getAllCertifications()
                }
                .onError { }
        }
    }
}