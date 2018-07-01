package com.gilson.githubviewer.di

import android.content.Context
import com.gilson.githubviewer.data.network.repository.RepositoryRest
import com.gilson.githubviewer.data.network.interceptors.CacheInterceptor
import com.gilson.githubviewer.data.network.interceptors.RequestHeaderInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesHeaderInterceptor(): RequestHeaderInterceptor {
        return RequestHeaderInterceptor()
    }

    @Provides
    @Singleton
    fun providesCacheInterceptor(): CacheInterceptor {
        return CacheInterceptor()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(context: Context,
                             headerInterceptor: RequestHeaderInterceptor,
                             cacheInterceptor: CacheInterceptor): OkHttpClient {
        val cache = Cache(File(context.getCacheDir(), "http-cache"), 10 * 1024 * 1024)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(headerInterceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        })
        httpClient.addNetworkInterceptor(cacheInterceptor).cache(cache)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideGithubRest(retrofit: Retrofit): RepositoryRest {
        return retrofit.create(RepositoryRest::class.java)
    }
}