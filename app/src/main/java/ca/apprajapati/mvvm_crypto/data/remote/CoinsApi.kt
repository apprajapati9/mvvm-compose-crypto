package ca.apprajapati.mvvm_crypto.data.remote

import ca.apprajapati.mvvm_crypto.data.remote.dto.CoinDetailDto
import ca.apprajapati.mvvm_crypto.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CoinsApi {

    // https://api.coinpaprika.com/v1/coins/
    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinDto>

    //https://api.coinpaprika.com/v1/coins/btc-bitcoin
    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(@Path("coinId") id: String) : CoinDetailDto

}