package org.itsolutions.mydivelog.domain.model.results

sealed interface DataError : Error {
    enum class Network : DataError {
        REQUEST_TIMEOUT,
        NO_INTERNET,
        UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL,
        ELEMENT_ALREADY_EXISTS,
        CERTIFICATE_ALREADY_EXISTS,
        UNKNOWN
    }
}