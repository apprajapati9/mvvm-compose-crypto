package ca.apprajapati.mvvm_crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.apprajapati.mvvm_crypto.presentation.Screen
import ca.apprajapati.mvvm_crypto.presentation.coin_list.CoinsListViewModel
import ca.apprajapati.mvvm_crypto.domain.use_case.get_coins.GetCoinsUseCase
import ca.apprajapati.mvvm_crypto.presentation.coin_list.component.CoinListScreen
import ca.apprajapati.mvvm_crypto.ui.theme.MvvmcryptoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val model : CoinsListViewModel = ViewModelProvider(
            this,
            ViewModelFactory(GetCoinsUseCase())
        )[CoinsListViewModel::class.java]

        setContent {
            MvvmcryptoTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()


                    NavHost(navController = navController,
                        startDestination = Screen.CoinListScreen.route) {

                        composable (route = Screen.CoinListScreen.route) {
                            CoinListScreen(navController= navController,
                                viewModel = model
                            )
                        }
                    }

                }
            }
        }
    }

    class ViewModelFactory(private val useCase : GetCoinsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CoinsListViewModel::class.java)){
                return CoinsListViewModel(useCase) as T
            }
            throw IllegalArgumentException("Unknown viewmodel class")
        }
    }
}
