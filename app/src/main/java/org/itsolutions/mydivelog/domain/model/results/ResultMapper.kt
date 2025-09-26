package org.itsolutions.mydivelog.domain.model.results

import org.itsolutions.mydivelog.R

fun DataError.toMessageResource(): Int {
    return when (this) {
        DataError.Local.DISK_FULL -> TODO()
        DataError.Local.ELEMENT_ALREADY_EXISTS -> TODO()
        DataError.Local.CERTIFICATE_ALREADY_EXISTS -> R.string.error_certificate_already_exists
        DataError.Local.UNKNOWN -> TODO()
        DataError.Network.REQUEST_TIMEOUT -> TODO()
        DataError.Network.NO_INTERNET -> TODO()
        DataError.Network.UNKNOWN -> TODO()
    }
}