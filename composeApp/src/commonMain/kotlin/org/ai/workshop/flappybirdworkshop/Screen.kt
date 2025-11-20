package org.ai.workshop.flappybirdworkshop

// Navigation state for simple screen switching across platforms
sealed class Screen {
    data object Start : Screen()
    data object Game : Screen()
    data object Score : Screen()
}
