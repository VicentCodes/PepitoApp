package com.vicentcodes.pepitoarrive.di

import com.vicentcodes.pepitoarrive.data.repository.PepitoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePepitoRepository(
        okHttpClient: OkHttpClient
    ): PepitoRepository {
        return PepitoRepository(okHttpClient)
    }
}