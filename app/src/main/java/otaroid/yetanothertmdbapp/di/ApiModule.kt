package otaroid.yetanothertmdbapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import otaroid.yetanothertmdbapp.BuildConfig
import otaroid.yetanothertmdbapp.common.Konstants
import otaroid.yetanothertmdbapp.data.remote.TMDBApi
import otaroid.yetanothertmdbapp.data.repository.TVShowsRepositoryImpl
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

const val LOGGING = "logging"
const val AUTH = "AUTH"

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private val TIMEOUT = 15L

    @Provides
    @Singleton
    @Named(AUTH)
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val initialRequest = chain.request()
            val initialRequestUrl = initialRequest.url
            val newUrl =
                initialRequestUrl.newBuilder().addQueryParameter("api_key", Konstants.TMDB_API_KEY)
                    .build()

            val newRequestBuilder = initialRequest.newBuilder().url(newUrl)
            val newRequest = newRequestBuilder.build()
            chain.proceed(newRequest)
        }
    }


    @Provides
    @Singleton
    @Named(LOGGING)
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named(AUTH) authInterceptor: Interceptor,
        @Named(LOGGING) loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            addInterceptor(authInterceptor)

        }
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {


        return Retrofit.Builder()
            .baseUrl(Konstants.BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TMDBApi {
        return retrofit.create(TMDBApi::class.java)
    }
}