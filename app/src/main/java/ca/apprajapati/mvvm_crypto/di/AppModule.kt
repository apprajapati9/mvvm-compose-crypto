package ca.apprajapati.mvvm_crypto.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.apprajapati.mvvm_crypto.common.Constants
import ca.apprajapati.mvvm_crypto.data.remote.CoinsApi
import ca.apprajapati.mvvm_crypto.data.repository.CoinRepositoryImpl
import ca.apprajapati.mvvm_crypto.domain.repository.CoinRepository
import ca.apprajapati.mvvm_crypto.domain.use_case.get_coin.GetCoinDetailsUseCase
import ca.apprajapati.mvvm_crypto.domain.use_case.get_coins.GetCoinsUseCase
import ca.apprajapati.mvvm_crypto.presentation.coin_list.CoinsListViewModel
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



    /*
        Below are the examples of abstracted DI, where no need to pass @Inject constructor
        Check Readme.md for more information
        TODO: would be great if we could do the same for viewmodels, look for such solution if exists/possible to.
     */

    @Provides
    fun providesGetCoinUseCase(repository: CoinRepository): GetCoinsUseCase {
        return GetCoinsUseCase(repository)
    }

    @Provides
    fun providesGetCoinDetailUseCase(repository: CoinRepository): GetCoinDetailsUseCase {
        return GetCoinDetailsUseCase(repository)
    }

}