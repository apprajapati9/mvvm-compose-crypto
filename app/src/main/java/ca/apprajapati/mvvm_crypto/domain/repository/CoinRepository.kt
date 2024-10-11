package ca.apprajapati.mvvm_crypto.domain.repository

import ca.apprajapati.mvvm_crypto.data.remote.dto.CoinDetailDto
import ca.apprajapati.mvvm_crypto.data.remote.dto.CoinDto

interface CoinRepository{
    suspend fun getCoins() : List<CoinDto>
    suspend fun getCoinDetails(coinId : String) : CoinDetailDto
}