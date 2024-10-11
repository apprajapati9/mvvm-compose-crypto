package ca.apprajapati.mvvm_crypto.presentation

sealed class Screen(val route: String){
    data object CoinListScreen : Screen("coin_list")
    data object CoinDetailScreen : Screen("coin_detail")
}
