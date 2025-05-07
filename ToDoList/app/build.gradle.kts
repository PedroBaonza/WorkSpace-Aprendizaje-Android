plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.finalretrofittest.to_dolist"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.finalretrofittest.to_dolist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room
    implementation (libs.androidx.room.runtime)
    testImplementation (libs.androidx.room.testing)

    // JUnit y Hamcrest
    testImplementation (libs.junit)
    testImplementation (libs.hamcrest)

    // Mockito
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.kotlin)

    // Espresso
    androidTestImplementation (libs.androidx.espresso.core.v351)
    androidTestImplementation (libs.androidx.junit.v115)

    // ViewModel y LiveData
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
}