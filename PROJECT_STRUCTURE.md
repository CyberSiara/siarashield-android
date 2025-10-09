# Example Project Structure

Here's how to structure your Android project to use SiaraShield:

```
MyApp/
├── app/
│   ├── build.gradle
│   ├── src/
│   │   └── main/
│   │       ├── AndroidManifest.xml
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── example/
│   │       │           └── myapp/
│   │       │               ├── MainActivity.java
│   │       │               └── SiaraShieldService.java
│   │       └── res/
│   │           ├── layout/
│   │           │   └── activity_main.xml
│   │           └── values/
│   │               └── strings.xml
│   └── proguard-rules.pro
├── build.gradle
├── gradle.properties
├── settings.gradle
└── gradlew
```

## Key Files

### Project build.gradle
```gradle
// Top-level build file
plugins {
    id 'com.android.application' version '8.6.0' apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### App build.gradle
```gradle
plugins {
    id 'com.android.application'
}

android {
    namespace 'com.example.myapp'
    compileSdk 35

    defaultConfig {
        applicationId "com.example.myapp"
        minSdk 21
        targetSdk 35
        versionCode 1
        versionName "1.0"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation 'com.github.CyberSiara:siarashield-android:1.4'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.8.0'
}
```

### AndroidManifest.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.MyApp">
        
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        
        <service
            android:name=".SiaraShieldService"
            android:enabled="true"
            android:exported="false" />
            
    </application>
</manifest>
```
