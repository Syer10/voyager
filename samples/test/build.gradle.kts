plugins {
    kotlin("android")
    id("com.android.application")
}

setupModuleForAndroidxCompose(
        composeCompilerVersion = libs.versions.composeCompiler.get(),
        withKotlinExplicitMode = false,
)

android {
    defaultConfig {
        applicationId = "cafe.adriel.voyager.sample.test"
    }
}

dependencies {
    implementation(projects.voyagerNavigator)
    implementation(projects.voyagerTabNavigator)
    implementation(projects.voyagerBottomSheetNavigator)
    implementation(projects.voyagerTransitions)
    implementation(projects.voyagerAndroidx)

    implementation(libs.compose.activity)
    implementation(libs.compose.compiler)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.materialIcons)
}
