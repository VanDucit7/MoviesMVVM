package com.trotalab.moviesmvvm.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.trotalab.moviesmvvm.data.datasource.MoviesApi
import com.trotalab.moviesmvvm.data.repository.MoviesRepository
import com.trotalab.moviesmvvm.utils.BASE_URL
import com.vn.fsoft.gstlib.core.network.NetworkConnectionInterceptor
import com.vn.fsoft.gstlib.core.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideNetworkConnectionInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(
            context
        )
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(
                AppConstants.TIME_OUT_SECONDS.toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                AppConstants.TIME_OUT_SECONDS.toLong(),
                TimeUnit.SECONDS
            )
            .addInterceptor(networkConnectionInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideMainService(retrofit: Retrofit.Builder): MoviesApi {
        return retrofit
            .build()
            .create(MoviesApi::class.java)
    }


//    @Singleton
//    @Provides
//    fun provideBlogDb(@ApplicationContext context: Context): AppDB {
//        return Room.databaseBuilder(
//            context.applicationContext,
//            AppDB::class.java,
//            AppDB.DB_NAME
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideBlogDAO(db: AppDB): EmployeeDAO {
//        return db.getEmployeeDao()
//    }

    @Singleton
    @Provides
    fun providesUserRepository(service: MoviesApi): MoviesRepository =
        MoviesRepository(service)
}