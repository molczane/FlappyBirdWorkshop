package org.ai.workshop.flappybirdworkshop

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.scale
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var screen by remember { mutableStateOf<Screen>(Screen.Start) }
        // Minimal session scores list kept in memory; Phase 5 will expand.
        var lastRunScore by remember { mutableStateOf(0) }

        when (screen) {
            Screen.Start -> StartScreen(onPlay = { screen = Screen.Game })
            Screen.Game -> GameScreen(onGameOver = { score ->
                lastRunScore = score
                screen = Screen.Score
            })
            Screen.Score -> ScoreScreen(lastScore = lastRunScore, onPlayAgain = { screen = Screen.Start })
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
private fun GameScreen(onGameOver: (score: Int) -> Unit) {
    // ---- Phase 2: Gameplay Core ----
    // Logical playfield (portrait) with letterboxing
    val W = 288f
    val H = 512f
    val groundH = 64f

    // Bird physics
    var birdY by remember { mutableStateOf(H / 2f) }
    var birdX by remember { mutableStateOf(W * 0.35f) }
    var birdVy by remember { mutableStateOf(0f) }
    val gravity = 900f // px/s^2 in logical space
    val flapImpulse = -280f // px/s (instant velocity set)
    val birdSize = Size(34f, 24f)

    // Pipe state (single pair)
    var pipeX by remember { mutableStateOf(W + 40f) }
    var gapCenterY by remember { mutableStateOf(H / 2f) }
    val gapSize = 120f
    val pipeW = 52f
    val scrollSpeed = 120f // px/s

    // Score
    var score by remember { mutableStateOf(0) }
    var passed by remember { mutableStateOf(false) }

    // Input flag (debounced per frame)
    var flapRequested by remember { mutableStateOf(false) }

    // Game loop
    LaunchedEffect(Unit) {
        var lastNanos = 0L
        while (true) {
            val now = withFrameNanos { it }
            if (lastNanos == 0L) {
                lastNanos = now
                continue
            }
            val dt = ((now - lastNanos) / 1_000_000_000.0f).coerceIn(0f, 1f / 15f)
            lastNanos = now

            // Apply input
            if (flapRequested) {
                birdVy = flapImpulse
                flapRequested = false
            }

            // Physics integration (simple Euler)
            birdVy += gravity * dt
            birdY += birdVy * dt

            // Scroll pipes
            pipeX -= scrollSpeed * dt
            if (pipeX + pipeW < 0f) {
                pipeX = W + 20f
                // Randomize gap within safe range
                val minY = 80f
                val maxY = H - groundH - 80f
                gapCenterY = (minY + Random.nextFloat() * (maxY - minY))
                passed = false
            }

            // Score when bird passes pipe center
            if (!passed && pipeX + pipeW / 2f < birdX) {
                score += 1
                passed = true
            }

            // Collision detection (AABB)
            val birdRect = Rect(Offset(birdX - birdSize.width / 2f, birdY - birdSize.height / 2f), birdSize)
            val topPipeRect = Rect(
                left = pipeX,
                top = 0f,
                right = pipeX + pipeW,
                bottom = gapCenterY - gapSize / 2f
            )
            val bottomPipeRect = Rect(
                left = pipeX,
                top = gapCenterY + gapSize / 2f,
                right = pipeX + pipeW,
                bottom = H - groundH
            )

            val hitPipe = birdRect.overlaps(topPipeRect) || birdRect.overlaps(bottomPipeRect)
            val outOfBounds = (birdY - birdSize.height / 2f < 0f) || (birdY + birdSize.height / 2f > H - groundH)
            if (hitPipe || outOfBounds) {
                onGameOver(score)
                break
            }
        }
    }

    Box(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && event.key == Key.Spacebar) {
                    flapRequested = true
                    true
                } else false
            }
            .pointerInput(Unit) {
                detectTapGestures(onTap = { flapRequested = true })
            },
        contentAlignment = Alignment.TopCenter
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scale = min(size.width / W, size.height / H)
            val dx = (size.width - W * scale) / 2f
            val dy = (size.height - H * scale) / 2f

            translate(left = dx, top = dy) {
                scale(scale) {
                    // Background
                    drawRect(color = Color(0xFF70C5CE), size = Size(W, H))
                    // Ground
                    drawRect(color = Color(0xFFDED895), topLeft = Offset(0f, H - groundH), size = Size(W, groundH))

                    // Pipes
                    // Top
                    drawRect(color = Color(0xFF5AC54F), topLeft = Offset(pipeX, 0f), size = Size(pipeW, max(0f, gapCenterY - gapSize / 2f)))
                    // Bottom
                    val bottomTop = gapCenterY + gapSize / 2f
                    drawRect(color = Color(0xFF5AC54F), topLeft = Offset(pipeX, bottomTop), size = Size(pipeW, H - groundH - bottomTop))

                    // Bird (simple rectangle placeholder)
                    drawRect(
                        color = Color(0xFFFFEB3B),
                        topLeft = Offset(birdX - birdSize.width / 2f, birdY - birdSize.height / 2f),
                        size = birdSize
                    )
                }
            }
        }

        // HUD
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = score.toString(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .safeContentPadding()
            )
        }
    }
}

@Composable
private fun ScoreScreen(lastScore: Int, onPlayAgain: () -> Unit) {
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Last Score: $lastScore")
        Button(onClick = onPlayAgain) { Text("Play Again") }
    }
}