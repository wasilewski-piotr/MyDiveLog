package org.itsolutions.mydivelog.data.mappers

import org.itsolutions.mydivelog.data.local.database.entity.CertificateEntity
import org.itsolutions.mydivelog.domain.model.Certificate

fun CertificateEntity.toDomain() = Certificate(
    id = id,
    certificateName = certificateName,
    certificateNumber = certificateNumber,
    organization = organization,
    issueDate = issueDate,
    issuedBy = issuedBy,
    issuerId = issuerId
)

fun Certificate.toEntity() = CertificateEntity(
    id = id,
    certificateName = certificateName,
    certificateNumber = certificateNumber,
    organization = organization,
    issueDate = issueDate,
    issuedBy = issuedBy,
    issuerId = issuerId
)