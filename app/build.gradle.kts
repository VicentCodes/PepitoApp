plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}


apply(plugin = "kotlin-kapt")

android {
    namespace = "com.vicentcodes.pepitoarrive"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vicentcodes.pepitoarrive"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dagger-Hilt
    implementation(libs.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)


    // Lifecycle / ViewModel
    implementation (libs.androidx.lifecycle.runtime.ktx.v287)
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    // OkHttp
    implementation (libs.okhttp)
    implementation (libs.okhttp.sse)
    implementation (libs.logging.interceptor)


    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    // WorkManager
    implementation (libs.androidx.work.runtime.ktx)

    // moshi.Json
    //noinspection UseTomlInstead
    implementation ("com.squareup.moshi:moshi-kotlin:1.12.0")

}

kapt {
    correctErrorTypes = true
}