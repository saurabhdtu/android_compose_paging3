package com.sample.compose.ui.screen.dogs

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.sample.compose.ui.theme.ComposeSampleTheme

data class BreedUiItem(
    val breedName: String,
    val description: String,
    val maxLife: Int,
    val minLife: Int,
    val hypoallergic: Boolean
)

@Composable
fun BreedListScreen(breedViewModel: BreedViewModel) {
    val list = breedViewModel.list.collectAsLazyPagingItems()

    if (list.loadState.refresh is LoadState.Loading)
        return Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(size = 64.dp))
        }

    if (list.loadState.refresh is LoadState.Error)
        Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_SHORT).show()


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list.itemCount) {
            BreedCard(breedUiItem = list[it])
        }
        item {
            if (list.loadState.append is LoadState.Loading)
                CircularProgressIndicator()
        }
    }

}

@Composable
fun BreedCard(breedUiItem: BreedUiItem?) {
    if (breedUiItem == null) return
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(8.dp)) {
            Text(
                text = breedUiItem.breedName,
                color = Color(0xFF000000),
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = breedUiItem.description, color = Color(0xFF454545))
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = "Max life: ${breedUiItem.maxLife}",
                    color = Color(0xFF454545),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Min life: ${breedUiItem.minLife}",
                    color = Color(0xFF454545),
                    fontSize = 12.sp
                )
            }

        }
    }
}


@Preview
@Composable
fun BreedCardPreview() {
    ComposeSampleTheme {
        BreedCard(
            breedUiItem = BreedUiItem(
                breedName = "breedName",
                description = "description",
                maxLife = 10,
                minLife = 5,
                hypoallergic = true
            )
        )
    }
}