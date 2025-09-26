package org.itsolutions.mydivelog.data.local.repository

import org.itsolutions.mydivelog.data.local.database.dao.CertificateDao
import org.itsolutions.mydivelog.data.local.database.entity.CertificateEntity
import org.itsolutions.mydivelog.data.mappers.databaseCall
import org.itsolutions.mydivelog.data.mappers.toDomain
import org.itsolutions.mydivelog.data.mappers.toEntity
import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import org.itsolutions.mydivelog.domain.model.results.DataError
import org.itsolutions.mydivelog.domain.model.results.Result
import org.itsolutions.mydivelog.domain.repository.CertificateRepository
import javax.inject.Inject

class CertificateRepositoryImpl @Inject constructor(
    private val certificateDao: CertificateDao
) : CertificateRepository {

    override suspend fun getDistinctOrganizations(): List<DiveOrganization> {
        return certificateDao.getDistinctOrganizations()
    }

    override suspend fun getAllCertifications(): List<Certificate> {
        return certificateDao.getAllCertifications().map { it.toDomain() }
    }

    override suspend fun createCertificate(certificate: Certificate): Result<Unit, DataError> {
        return databaseCall(certificate::class) {
            certificateDao.createCertificate(certificate.toEntity())
        }
    }

    override suspend fun deleteCertificate(certificate: Certificate): Result<Unit, DataError> {
        return databaseCall(certificate::class) {
            certificateDao.deleteCertificate(certificate.toEntity())
        }
    }
}