import org.gradle.declarative.dsl.schema.FqName.Empty.packageName

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.apollographql.apollo3").version("3.7.3")
}

apollo {
    service("service") {
        packageName.set("com.francotte")
    }
}

android {
    namespace = "com.francotte.apollotest"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.francotte.apollotest"
        minSdk = 26
        targetSdk = 36
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.3")

    // --- Optionnel mais recommandé selon ton usage ---

    // Cache normalisé en mémoire (simple)
    implementation("com.apollographql.apollo3:apollo-normalized-cache:3.7.3")

    // Cache normalisé SQLite (persistance disque)
    implementation("com.apollographql.apollo3:apollo-normalized-cache-sqlite:3.7.3")

    // Intégration OkHttp (si tu veux personnaliser le client, intercepteurs, etc.)
    implementation("com.apollographql.apollo3:apollo-okhttp3:3.7.3")

    // Adapters utiles pour certains scalars (UUID, Instant, etc.)
    implementation("com.apollographql.apollo3:apollo-adapters:3.7.3")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}