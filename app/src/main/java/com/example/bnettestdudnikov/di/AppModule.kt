package com.example.bnettestdudnikov.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.bnettestdudnikov.data.db.AppDataBase
import com.example.bnettestdudnikov.data.db.DrugDBDao
import com.example.bnettestdudnikov.data.network.RetroInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    val BASE_URL = "http://shans.d2.i-partner.ru/"

    @Provides
    @Singleton
    fun getRetroInterface(retrofit: Retrofit): RetroInterface {
        return retrofit.create(RetroInterface::class.java)
    }

    @Provides
    @Singleton
    fun getNetworkInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getDB(context: Application): AppDataBase {
        return AppDataBase.getDataBase(context)
    }

    @Provides
    @Singleton
    fun getAppDBDao(database: AppDataBase): DrugDBDao {
        return database.getDrugDBDao()
    }

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}