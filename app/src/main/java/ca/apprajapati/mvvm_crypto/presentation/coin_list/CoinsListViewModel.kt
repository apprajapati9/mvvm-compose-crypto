package ca.apprajapati.mvvm_crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.apprajapati.mvvm_crypto.common.Resource
import ca.apprajapati.mvvm_crypto.domain.model.CoinListState
import ca.apprajapati.mvvm_crypto.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(val coinListUseCase: GetCoinsUseCase) : ViewModel() {


    //Using stateflow instead of State from compose because it is a superior approach
    // 1. keeps viewmodel compose free 2. survive process death (both state and stateflow do)
    private val _coinListState = MutableStateFlow(CoinListState())
    val coinListState : StateFlow<CoinListState> get() = _coinListState

    init {
        getCoinList()
    }

    private fun getCoinList(){
        coinListUseCase().onEach {
            result ->
                when(result){
                    is Resource.Success -> {
                        _coinListState.value = CoinListState(coins = result.data)
                    }

                    is Resource.Error -> {
                        _coinListState.value = CoinListState(error = result.message)
                    }

                    is Resource.Loading ->
                        _coinListState.value = CoinListState(isLoading = true)
                }
        }.launchIn(viewModelScope)

    }

}