plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mytabeltimerforjava"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mytabeltimerforjava"
        minSdk = 24
        targetSdk = 34
        versionCode = 5
        versionName = "2.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-Xlint:-deprecation") // 忽略过时 API 警告
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation(libs.lifecycle.viewmodel.android)
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0") // Java 项目使用 annotationProcessor
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
