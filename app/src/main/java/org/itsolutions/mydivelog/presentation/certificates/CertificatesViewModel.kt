package org.itsolutions.mydivelog.presentation.certificates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import org.itsolutions.mydivelog.domain.repository.CertificateRepository
import javax.inject.Inject

@HiltViewModel
class CertificatesViewModel @Inject constructor(
    private val certificateRepository: CertificateRepository
) : ViewModel() {

    private val _organizations = MutableStateFlow<List<DiveOrganization>>(emptyList())
    val organizations: StateFlow<List<DiveOrganization>> = _organizations.asStateFlow()

    init {
        getDistinctOrganizations()
    }

    fun getDistinctOrganizations() {
        viewModelScope.launch {
            _organizations.value = certificateRepository.getDistinctOrganizations()
        }
    }
}