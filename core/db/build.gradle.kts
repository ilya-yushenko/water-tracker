plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.realm.kotlin)
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvmToolchain(11)

    sourceSets {
        commonMain.dependencies {
            implementation(project(":common"))
            implementation(libs.realm.kotlin)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)
            implementation("com.benasher44:uuid:0.8.4")
        }
    }
}

android {
    namespace = "com.tide.db"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}