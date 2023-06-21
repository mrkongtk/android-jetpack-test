package com.yourparkingspace.androidtechtest.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import com.yourparkingspace.androidtechtest.ui.theme.AndroidTechTestTheme
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.StatedBoolean

class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.simpleName

    private var isLoading = StatedBoolean(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTechTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainView(isLoading)

                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun MainView(
    isLoading: StatedBoolean,
) {
    LoadingView(isLoading)
}

@Composable
fun LoadingView(isLoading: StatedBoolean) {
    if (isLoading.value){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainTodoViewPreview() {
    AndroidTechTestTheme {
        MainView(StatedBoolean(true))
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTechTestTheme {
        Greeting("Android")
    }
}