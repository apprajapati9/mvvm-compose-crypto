package ca.apprajapati.mvvm_crypto.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.apprajapati.mvvm_crypto.common.Resource
import ca.apprajapati.mvvm_crypto.domain.use_case.get_coin.GetCoinDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach

class CoinDetailViewModel(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){



    private val _coinDetailState = MutableStateFlow(CoinDetailState())
    val coinDetailState: StateFlow<CoinDetailState> get() = _coinDetailState

    init {
        savedStateHandle.get<String>("coinId")?.let {
            coinId ->
                getCoinDetails(coinId)
        }
    }


    private fun getCoinDetails(coinId : String){
        getCoinDetailsUseCase(coinId).onEach {
                result ->
            when(result){
                is Resource.Success -> {
                    _coinDetailState.value = CoinDetailState(details = result.data)
                }

                is Resource.Error -> {
                    _coinDetailState.value = CoinDetailState(error = result.message)
                }

                is Resource.Loading ->  CoinDetailState(isLoading = true)
            }
        }
    }

}