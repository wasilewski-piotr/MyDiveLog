package org.itsolutions.mydivelog.domain.model

import java.time.LocalDate

data class Certificate(
    val id: Int = 0,
    val certificateName: String,
    val certificateNumber: String,
    val organization: DiveOrganization,
    val issueDate: LocalDate,
    val issuedBy: String,
    val issuerId: String,
)