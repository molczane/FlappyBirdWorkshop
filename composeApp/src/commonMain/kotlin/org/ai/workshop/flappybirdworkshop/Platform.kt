package org.ai.workshop.flappybirdworkshop

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform