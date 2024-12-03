package com.zenpath.dev.learningcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenpath.dev.learningcompose.ui.theme.LearningComposeTheme

class TwoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val configuration = LocalConfiguration.current
            ScreenOrientation(configuration)
        }
    }
}

@Composable
fun Greeting2(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(16.dp))
        SearchBar()
        var title: List<String> = List(1000) {stringResource(id = R.string.ab1_inversions)}
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow(Modifier, title, R.drawable.ic_launcher_background)
        }
        HomeSection(R.string.align_your_body) {
            FavoriteCollectionGrid(title = title, paintId = R.drawable.ic_launcher_background)
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(value = "", onValueChange = {},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, "")},
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.LightGray,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = stringResource(R.string.placeholder_search)) },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(horizontal = 16.dp)
    )

}

@Composable
fun AlignYourBodyElement(modifier: Modifier = Modifier, resImg: Int, text: String){
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(resImg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape))
        Text(text = text,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun FavoriteCollectionCard(modifier: Modifier = Modifier, paintId: Int, text: String){
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Row(modifier = Modifier
            .height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = paintId),
                contentDescription = null,
                modifier = Modifier.width(80.dp),
                contentScale = ContentScale.Crop
                )
            Text(text = text,
                modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun FavoriteCollectionGrid(modifier: Modifier = Modifier, title: List<String>, paintId: Int){
    LazyHorizontalGrid(rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .height(168.dp)){
        items(items = title) { item ->
            FavoriteCollectionCard(modifier, paintId, item)
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(stringResource(title), style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp))
        content()
    }
}



@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier,
    title: List<String>,
    paintId: Int
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(items = title) { item ->
            AlignYourBodyElement(resImg = paintId, text = item)
        }
    }
}

@Composable
private fun NavigationMenu(modifier: Modifier = Modifier){
    NavigationBar(modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.btn_nav_home))
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.btn_nav_profile))
            })
    }
}

@Composable
private fun RailNavigationMenu(modifier: Modifier = Modifier){
    NavigationRail( containerColor = MaterialTheme.colorScheme.background
    ) {
        Column( modifier = modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.btn_nav_home))
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.btn_nav_profile))
                },
                selected = false,
                onClick = {}
            )
        }

    }
}



@Composable
fun MainView() {
    LearningComposeTheme {
        Scaffold(
            bottomBar = { NavigationMenu() }
        ) { padding ->
            Greeting2(Modifier.padding(padding))
        }
    }
}

@Composable
fun MainViewLandScape() {
    LearningComposeTheme {
        Surface (color = MaterialTheme.colorScheme.background) {
            Row {
                RailNavigationMenu()
                Greeting2()
            }
        }
    }
}

@Composable
fun ScreenOrientation(configuration: Configuration) {
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            MainViewLandScape()
        }
        else -> {
            MainView()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    LearningComposeTheme {
       SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyPreview() {
    LearningComposeTheme {
        AlignYourBodyElement(
            modifier = Modifier,
            resImg = R.drawable.ic_launcher_background,
            text = stringResource(R.string.ab1_inversions)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyRowPreview(){
    var title: List<String> = List(1000) {stringResource(id = R.string.ab1_inversions)}
    LearningComposeTheme {
        AlignYourBodyRow(Modifier, title, R.drawable.ic_launcher_background)
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionCardPreview(){
    LearningComposeTheme {
        FavoriteCollectionCard(
            paintId = R.drawable.ic_launcher_background,
            text = stringResource(id = R.string.fc2_nature_meditations),
            modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionGridPreview(){
    LearningComposeTheme {
        var title: List<String> = List(1000) {stringResource(id = R.string.ab1_inversions)}
        FavoriteCollectionGrid(title = title, paintId = R.drawable.ic_launcher_background)
    }
}

@Preview(showBackground = true, widthDp = 350)
@Composable
fun MainPreview(){
    LearningComposeTheme {
        MainView()
    }
}

@Preview(name = "Smartphone, Nokia X20",
    group = "Smartphone",
    showSystemUi = true,
    device = "spec:width=1080px,height=2400px,dpi=400",)
@Composable
fun HomeSectionPreview() {
    LearningComposeTheme {
        HomeSection(R.string.align_your_body) {
            var title: List<String> = List(1000) {stringResource(id = R.string.ab1_inversions)}
            AlignYourBodyRow(Modifier, title, R.drawable.ic_launcher_background)
        }
    }
}