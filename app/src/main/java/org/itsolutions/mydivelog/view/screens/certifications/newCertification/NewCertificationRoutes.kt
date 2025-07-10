package org.itsolutions.mydivelog.view.screens.certifications.newCertification

import kotlinx.serialization.Serializable

sealed interface NewCertificationRoutes {

    @Serializable
    data object ChooseOrganization : NewCertificationRoutes

    @Serializable
    data object CertificationData : NewCertificationRoutes

    @Serializable
    data object IssuerData : NewCertificationRoutes
}