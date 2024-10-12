package ca.apprajapati.mvvm_crypto.di

import ca.apprajapati.mvvm_crypto.common.Constants
import ca.apprajapati.mvvm_crypto.data.remote.CoinsApi
import ca.apprajapati.mvvm_crypto.data.repository.CoinRepositoryImpl
import ca.apprajapati.mvvm_crypto.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{


    @Provides
    @Singleton
    fun provideHTTPClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideCoinsApi(client: OkHttpClient): CoinsApi = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        .create(CoinsApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: CoinsApi) : CoinRepository {
        return CoinRepositoryImpl(api)
    }
}