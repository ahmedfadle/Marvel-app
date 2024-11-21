plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    id("androidx.navigation.safeargs.kotlin")
//    alias(libs.plugins.kapt)
    id ("com.google.devtools.ksp")
    id ("dagger.hilt.android.plugin")
    id("kotlin-parcelize")

}

android {
    namespace = "com.marvel.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.marvel.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding =  true
    }
    buildFeatures {
        dataBinding = true
    }

    packaging {
        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes +=  "META-INF/gradle/incremental.annotation.processors"
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //Retrofit
    implementation(libs.square.retrofit)
    implementation(libs.square.adapter.rxjava2)
    implementation(libs.square.converter.moshi)
    implementation (libs.jsoup)
    implementation (libs.converter.gson)

    //OkHttpClient
    implementation(libs.square.okhttp.logging.interceptor)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.google.gson)

//    implementation(libs.hilt.android.gradle.plugin)
//    implementation(libs.hilt.compiler.gradle.plugin)

    implementation (libs.hilt.android.gradle.plugin)
    ksp( libs.dagger.compiler) // Dagger compiler
    ksp (libs.hilt.compiler.gradle.plugin) // Hilt compiler


    implementation(libs.kotlinx.coroutines.play.services)

//    implementation(libs.androidx.navigation.safe.args.gradle.plugin)

    implementation(libs.androidx.runtime.livedata )
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
//    implementation(libs.androidx.fragment.ktx)
//    implementation ("androidx.fragment:fragment-ktx:1.5.1")



    implementation(libs.timber)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Room Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.room.compiler)


    implementation("androidx.paging:paging-runtime:3.2.0")


    //Navigation component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

//    implementation ("androidx.databinding:databinding-runtime:7.2.2") // Adjust to your AGP version

    //glide
    implementation("com.github.bumptech.glide:glide:4.14.2")
    implementation ("com.github.bumptech.glide:okhttp3-integration:4.15.1")
    ksp("com.github.bumptech.glide:ksp:4.14.2")

//    ksp ("com.google.devtools.ksp:symbol-processing-api:1.10.0-1.0.13")

}