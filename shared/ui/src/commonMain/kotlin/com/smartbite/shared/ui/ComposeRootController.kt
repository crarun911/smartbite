package com.smartbite.shared.ui

import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import org.koin.compose.koinInject
import androidx.compose.ui.graphics.ImageBitmap

@Composable
fun MealAnalysisRoot() {
    val viewModel: MealAnalysisViewModel = koinInject()

    var photoBytes by remember { mutableStateOf<ByteArray?>(null) }
    var photoBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    // If no image yet → simple UI asking user to capture one
    if (photoBytes == null) {
        Column(
            Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("SmartBite")
            Spacer(Modifier.height(16.dp))
            Text("Tap the button to capture a meal photo.\n(iOS camera coming next)", maxLines = 3)
            Spacer(Modifier.height(24.dp))

            Button(onClick = {
                // For now: we leave this blank for iOS until your CameraBridge integration
                // iOS will call this from Swift once CameraBridge is added.
            }) {
                Text("Capture Meal")
            }
        }
        return
    }

    // If image exists → show shared MealAnalysisScreen
    MealAnalysisScreen(
        viewModel = viewModel,
        photo = photoBitmap,
        onRetake = {
            viewModel.reset()
            photoBytes = null
            photoBitmap = null
        }
    )
}
