package com.yushenko.watertracker

enum class Platform {
    Android, iOS
}

expect fun getPlatform(): Platform