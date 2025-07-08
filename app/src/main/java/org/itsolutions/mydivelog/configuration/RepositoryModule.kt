package org.itsolutions.mydivelog.configuration

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.itsolutions.mydivelog.data.local.repository.CertificateRepositoryImpl
import org.itsolutions.mydivelog.domain.repository.CertificateRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCertificateRepository(
        impl: CertificateRepositoryImpl
    ): CertificateRepository
}