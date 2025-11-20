package org.ai.workshop.flappybirdworkshop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var screen by remember { mutableStateOf<Screen>(Screen.Start) }

        when (screen) {
            Screen.Start -> StartScreen(onPlay = { screen = Screen.Game })
            Screen.Game -> GameScreen(onGameOver = { screen = Screen.Score })
            Screen.Score -> ScoreScreen(onPlayAgain = { screen = Screen.Start })
        }
    }
}

@Composable
private fun StartScreen(onPlay: () -> Unit) {
    // Centered Play button with keyboard focus and Enter/Space activation
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        // Request focus on first composition so keyboard works immediately
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onPlay,
            modifier = Modifier
                .focusRequester(focusRequester)
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown && (event.key == Key.Enter || event.key == Key.Spacebar)) {
                        onPlay()
                        true
                    } else false
                }
        ) {
            Text("Play")
        }
    }
}

@Composable
private fun GameScreen(onGameOver: () -> Unit) {
    // Placeholder for gameplay screen; navigation wired per Phase 1
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Game Screen — TODO: implement gameplay core")
    }
}

@Composable
private fun ScoreScreen(onPlayAgain: () -> Unit) {
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Score Screen — TODO: session scores")
        Button(onClick = onPlayAgain) { Text("Play Again") }
    }
}