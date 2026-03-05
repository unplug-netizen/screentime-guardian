# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep Room entities
-keep class com.screentime.guardian.data.local.** { *; }

# Keep Firebase
-keep class com.google.firebase.** { *; }

# Keep Retrofit/OkHttp (if used in future)
-keep class retrofit2.** { *; }
-keepclassmembers class retrofit2.** { *; }

# Keep Compose
-keep class androidx.compose.** { *; }

# Keep Kotlin Coroutines
-keep class kotlinx.coroutines.** { *; }
