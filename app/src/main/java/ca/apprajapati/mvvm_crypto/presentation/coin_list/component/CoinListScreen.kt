package ca.apprajapati.mvvm_crypto.presentation.coin_list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ca.apprajapati.mvvm_crypto.presentation.Screen
import ca.apprajapati.mvvm_crypto.presentation.coin_list.CoinsListViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel : CoinsListViewModel = hiltViewModel() // can get it easily here using Hilt di
) {

    //val viewModel: CoinsListViewModel = viewModel()
    val state = viewModel.coinListState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(state.value.coins){
                coin ->
                    CoinListItem(coin = coin, onItemClick = {
                        navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                    })
            }
        }

        if(state.value.error.isNotBlank()){
            Text(text = state.value.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if(state.value.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}