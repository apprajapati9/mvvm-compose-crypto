package ca.apprajapati.mvvm_crypto.data.repository

import ca.apprajapati.mvvm_crypto.data.remote.dto.CoinDetailDto
import ca.apprajapati.mvvm_crypto.data.remote.dto.CoinDto
import ca.apprajapati.mvvm_crypto.data.remote.CoinsApi
import ca.apprajapati.mvvm_crypto.domain.repository.CoinRepository

class CoinRepositoryImpl(private val api : CoinsApi) : CoinRepository {

    override suspend fun getCoins() : List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinDetails(coinId: String) : CoinDetailDto {
        return api.getCoin(coinId)
    }
}