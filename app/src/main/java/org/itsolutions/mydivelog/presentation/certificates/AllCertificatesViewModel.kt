package org.itsolutions.mydivelog.presentation.certificates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.domain.repository.CertificateRepository
import javax.inject.Inject

@HiltViewModel
class AllCertificatesViewModel @Inject constructor(
    private val certificateRepository: CertificateRepository
) : ViewModel() {

    private val _certifications = MutableStateFlow<List<Certificate>>(emptyList())
    val certifications: StateFlow<List<Certificate>> = _certifications.asStateFlow()

    init {
        getAllCertificates()
    }

    fun getAllCertificates() {
        viewModelScope.launch {
            _certifications.value = certificateRepository.getAllCertificates()
        }
    }
}