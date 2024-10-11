package ca.apprajapati.mvvm_crypto.presentation.coin_detail

import ca.apprajapati.mvvm_crypto.domain.model.CoinDetail

data class CoinDetailState(
    val details : CoinDetail ?= null,
    val error : String ?= "",
    val isLoading: Boolean = false
)
