package ca.apprajapati.mvvm_crypto.data.remote

import ca.apprajapati.mvvm_crypto.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetRetrofit {

    val api = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CoinsApi::class.java)

}