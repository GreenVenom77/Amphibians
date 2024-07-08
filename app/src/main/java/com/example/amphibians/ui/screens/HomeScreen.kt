package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import coil.compose.AsyncImage
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibiansInfo
import com.example.amphibians.ui.AmphibiansUiState
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        when (amphibiansUiState) {
            is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is AmphibiansUiState.Success -> InfoGridScreen(amphibiansInfo = amphibiansUiState.info)
            is AmphibiansUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
fun InfoGridScreen(
    amphibiansInfo: List<AmphibiansInfo>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
    ) {
        items(items = amphibiansInfo) {
            info -> AmphibiansInfoCard(
            amphibiansInfo = info,
            modifier = modifier
                .padding(4.dp)
                .fillMaxSize()
            )
        }
    }
}

@Composable
fun AmphibiansInfoCard(
    amphibiansInfo: AmphibiansInfo,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { /*TODO*/ },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        modifier = modifier.padding(dimensionResource(R.dimen.small_padding))
    ) {
        Text(
            text = amphibiansInfo.name,
            style = MaterialTheme.typography.displaySmall,
            modifier = modifier
        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(amphibiansInfo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = amphibiansInfo.name,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1.7f)
        )
        Text(
            text = amphibiansInfo.description,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier
        )
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = null,
        modifier = modifier.size(200.dp)
    )
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen({})
    }
}

@Preview
@Composable
fun CardPreview() {
    AmphibiansTheme {
        val mockData = AmphibiansInfo("fdgfsdg", "frog", "fdgdfgdfgfgddfgdffddfgdfgdfdfgdfgdfgdf", "")
        AmphibiansInfoCard(amphibiansInfo = mockData)
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    AmphibiansTheme {
        val mockData = List(10) { AmphibiansInfo("fdgfsdg", "$it", "fdgdfgdfgfgddfgdffddfgdfgdfdfgdfgdfgdf", "") }
        InfoGridScreen(amphibiansInfo = mockData)
    }
}