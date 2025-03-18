package com.example.noteapp.di

import com.example.noteapp.api.AuthAPI
import com.example.noteapp.api.AuthInterceptor
import com.example.noteapp.api.NoteAPI
import com.example.noteapp.api.RefreshTokenAPI
import com.example.noteapp.api.UserAPI
import com.example.noteapp.utilities.AuthRetrofit
import com.example.noteapp.utilities.Constants
import com.example.noteapp.utilities.RestRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
//    authentication retrofit instance
    @Singleton
    @AuthRetrofit
    @Provides
    fun provideAuthRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().
        addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    //    Rest retrofit instance
    @Singleton
    @RestRetrofit
    @Provides
    fun provideRestRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).
            client(okHttpClient).
            build()
    }

//    auth API instance
    @Singleton
    @Provides
    fun provideAuthAPI(@AuthRetrofit retrofit: Retrofit.Builder): AuthAPI {
        return retrofit.baseUrl(Constants.BASE_AUTH_URL)
            .build().
            create(AuthAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRefreshTokenAPI(@AuthRetrofit retrofit: Retrofit.Builder): RefreshTokenAPI {
        return retrofit.baseUrl(Constants.REFRESH_BASE_URL).
        build().
        create(RefreshTokenAPI::class.java)
    }

//    user API instance

    @Singleton
    @Provides
    fun provideUserAPI(@RestRetrofit retrofit: Retrofit): UserAPI {

        return retrofit.create(UserAPI::class.java)

    }

//    note API instance

    @Singleton
    @Provides
    fun provideNoteAPI(@RestRetrofit retrofit: Retrofit): NoteAPI {

        return retrofit.create(NoteAPI::class.java)

    }
}