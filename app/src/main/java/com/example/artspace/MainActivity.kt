package com.example.artspace

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFFfafcfb)
                ) {
                    ArtSpaceScreen()
                }
        }
    }
}

data class Artwork(
        @DrawableRes val imageResourceId: Int,
        val title: String,
        val artist: String,
        val location: String,
        val year: Int
)

@SuppressLint("UnrememberedMutableState")
@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val artIndex = remember { mutableIntStateOf(0) }
    val artworks =
        listOf(
            Artwork(R.drawable.art,
                stringResource(R.string.lule_kutang),
                stringResource(R.string.thomas), stringResource(R.string.lolodorf), 1885),
            Artwork(R.drawable.art1,
                stringResource(R.string.femme), stringResource(R.string.joyce),
                stringResource(R.string.grand_zambi), 1889),
            Artwork(R.drawable.art2,
                stringResource(R.string.chef), stringResource(R.string.georges),
                stringResource(R.string.ngovayang), 1903)
        )

    
    Column (modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = stringResource(R.string.sap_artspace), fontWeight = FontWeight.Bold, fontSize = 30.sp, color =Color(0xFF082F49))
        Spacer(modifier = Modifier.height(24.dp))
        ArtworkDisplay(artwork = artworks[artIndex.value])
        Spacer(modifier = Modifier.height(10.dp))
        ArtworkInfo(artwork = artworks[artIndex.value])
        Spacer(modifier = Modifier.height(12.dp))
        NavigationButtons(
            onPreviousClick = {
                artIndex.value = (artIndex.value - 1 + artworks.size) % artworks.size
            },
            onNextClick = {
                artIndex.value = (artIndex.value + 1) % artworks.size
            }
        )
    }

}


@Composable
fun ArtworkDisplay(artwork: Artwork, modifier: Modifier = Modifier){
    Surface (
        modifier = modifier
            .size(452.dp)
            .padding(14.dp)
    ){
        Image(painter = painterResource(id = artwork.imageResourceId),
            contentDescription = artwork.title,
            modifier = Modifier.fillMaxSize()
            )
    }
}

@Composable
fun ArtworkInfo(artwork: Artwork, modifier: Modifier = Modifier){
    Column( modifier = modifier.padding(16.dp)) {
        Text(text = artwork.title, fontWeight = FontWeight.SemiBold, fontSize = 36.sp,textAlign = TextAlign.Center, color = Color(0xFF3F3F46))
        Text(text = "${artwork.artist}, ${artwork.location}, ${artwork.year}", Modifier.padding(0.dp,6.dp), color = Color(0xFF71717A))
    }
}

@Composable
fun NavigationButtons(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = onPreviousClick, modifier = Modifier.padding(4.dp, 0.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF082F49))) {
            Text(stringResource(R.string.pr_c_dent))
        }
        Button(onClick = onNextClick, modifier = modifier.padding(4.dp, 0.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0369A1))) {
            Text(stringResource(R.string.suivant))
        }
    }
}

@Suppress("VisualLintBounds")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}