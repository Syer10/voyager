package com.example.voyagersample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.voyagersample.ui.theme.VoyagerSampleTheme

class HomeScreen : AndroidScreen() {

    override val key: ScreenKey = super.key.also { println(it to this::class.qualifiedName) }

    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow
        Text("HomeScreen",
            Modifier
                .fillMaxSize()
                .clickable {
                    nav.push(PostListScreen())
                })
    }
}

class PostListScreen : AndroidScreen() {

    override val key: ScreenKey = super.key.also { println("PostListScreen: " + (it to this::class.qualifiedName)) }

    @Composable
    override fun Content() {
        Navigator(
            screen = PostDetailsScreen(0L),
            key = "ChildNavigator",
        ) { nav ->
            Column {
                LazyColumn(Modifier.weight(1f)) {
                    items(listOf(1L, 2L, 3L, 4L, 5L)) {
                        TextButton(onClick = {
                            nav.push(PostDetailsScreen(it))
                        }, Modifier.fillMaxWidth()) {
                            Text("Post $it")
                        }
                    }
                }
                Divider()
                Box(Modifier.weight(1f)) {
                    CurrentScreen()
                }
            }
        }
    }
}

class PostDetailsScreen(val postId: Long) : AndroidScreen() {

    override val key: ScreenKey = super.key.also { println("PostDetailsScreen: " + (it to this::class.qualifiedName)) }

    @Composable
    override fun Content() {
        Text("Post $postId")
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VoyagerSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigator(
                        HomeScreen(),
                        onBackPressed = null,
                        key = "HomeNavigator",
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VoyagerSampleTheme {
        Greeting("Android")
    }
}
