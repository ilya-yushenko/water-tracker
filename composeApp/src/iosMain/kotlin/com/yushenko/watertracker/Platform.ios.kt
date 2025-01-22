package com.yushenko.watertracker

//class IOSPlatform: Platform {
//    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
//}

actual fun getPlatform(): Platform = Platform.iOS