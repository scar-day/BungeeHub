dependencies {
    implementation(project(":configuration"))

    implementation("eu.okaeri:okaeri-platform-velocity:0.4.39")

    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
}