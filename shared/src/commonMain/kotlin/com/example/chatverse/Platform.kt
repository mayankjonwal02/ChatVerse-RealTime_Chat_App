package com.example.chatverse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform