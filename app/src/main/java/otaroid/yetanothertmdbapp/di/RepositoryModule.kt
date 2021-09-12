package otaroid.yetanothertmdbapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import otaroid.yetanothertmdbapp.data.remote.TMDBApi
import otaroid.yetanothertmdbapp.data.repository.TVShowsRepositoryImpl
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTvShowsRepository(api: TMDBApi): TVShowsRepository {
        return TVShowsRepositoryImpl(api)
    }
}