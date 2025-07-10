package org.itsolutions.mydivelog.domain.repository

import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import org.itsolutions.mydivelog.domain.model.results.DataError
import org.itsolutions.mydivelog.domain.model.results.Result

interface CertificateRepository {
    suspend fun getDistinctOrganizations(): List<DiveOrganization>
    suspend fun getAllCertifications(): List<Certificate>
    suspend fun createCertificate(certificate: Certificate): Result<Unit, DataError>
    suspend fun deleteCertificate(certificate: Certificate): Result<Unit, DataError>
}