package org.itsolutions.mydivelog.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.itsolutions.mydivelog.domain.model.DiveOrganization
import java.time.LocalDate

@Entity(
    tableName = "certifications",
    primaryKeys = ["certificateNumber", "organization"]
)
data class CertificateEntity(
    val certificateName: String,
    val certificateNumber: String,
    val organization: DiveOrganization,
    val issueDate: LocalDate,
    val issuedBy: String,
    val issuerId: String,
)