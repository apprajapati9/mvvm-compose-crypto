package ca.apprajapati.mvvm_crypto.presentation.coin_list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ca.apprajapati.mvvm_crypto.domain.model.Coin


@Composable
fun CoinListItem(
    coin : Coin,
    onItemClick : (Coin) -> Unit
) {

    Row (modifier = Modifier.fillMaxSize()
        .clickable { onItemClick(coin) }
        .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis
        )

        Text(text = if(coin.isActive) "active" else "inactive",
            color = if(coin.isActive) Color.Green else Color.Red,
            fontStyle =  FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )


    }

}