package com.bytheproject.flashforge // Ensure this package name is correct

import android.os.Bundle
import androidx.activity.ComponentActivity // Import ComponentActivity for Compose
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme // For accessing theme colors/typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bytheproject.flashforge.ui.theme.FlashForgeAppTheme // Your app's Compose theme

class MainActivity : ComponentActivity() { // Inherit from ComponentActivity

    // No more ViewBinding needed for the Activity's root layout
    // private lateinit var binding: ActivityMainBinding
    // No more NavController for Fragment-based navigation at this level
    // private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content of the activity using Jetpack Compose
        setContent {
            // Apply your custom Material 3 theme
            FlashForgeAppTheme {
                // A Surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // Uses the black background from your theme
                ) {
                    // This is where your app's main UI structure will go.
                    // For now, let's put a placeholder.
                    // Later, this will be your NavHost for Compose navigation.
                    Greeting("FlashForge with Compose!")
                }
            }
        }

        // The old setupNavigation() and onSupportNavigateUp() for Fragment-based navigation
        // are no longer needed here. Navigation will be handled by Compose Navigation.
    }
}

// Simple placeholder Composable function
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello $name!",
            color = MaterialTheme.colorScheme.onBackground // Uses the white text color from your theme
        )
    }
}

// Preview for Android Studio (optional, but helpful)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlashForgeAppTheme {
        Greeting("Android")
    }
}