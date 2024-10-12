package ca.apprajapati.mvvm_crypto.di

import ca.apprajapati.mvvm_crypto.common.Constants
import ca.apprajapati.mvvm_crypto.data.remote.CoinsApi
import ca.apprajapati.mvvm_crypto.data.repository.CoinRepositoryImpl
import ca.apprajapati.mvvm_crypto.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideCoinsApi(): CoinsApi = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        .create(CoinsApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: CoinsApi) : CoinRepository {
        return CoinRepositoryImpl(api)
    }
}