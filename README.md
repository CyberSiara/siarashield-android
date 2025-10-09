# SiaraShield Android

[![JitPack](https://jitpack.io/v/CyberSiara/siarashield-android.svg)](https://jitpack.io/#CyberSiara/siarashield-android)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

SiaraShield Android is a comprehensive cybersecurity library for Android applications that provides advanced security features and threat detection capabilities.

## Features

- **Cyber Activity Monitoring**: Real-time monitoring of cyber activities
- **Threat Detection**: Advanced threat detection algorithms
- **Security Analytics**: Comprehensive security analytics and reporting
- **Easy Integration**: Simple integration with existing Android projects

## Installation

### Using JitPack

Add JitPack to your project's `build.gradle` file:

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency to your app's `build.gradle`:

```gradle
dependencies {
    implementation 'com.github.CyberSiara:siarashield-android:1.4'
}
```

### Manual Installation

1. Clone this repository
2. Add the `SiaraShield` module to your project
3. Add the dependency in your app's `build.gradle`:

```gradle
dependencies {
    implementation project(':SiaraShield')
}
```

## Usage

### Basic Implementation

```java
import com.cybersiara.app.CyberActivity;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize SiaraShield
        CyberActivity cyberActivity = new CyberActivity();
        cyberActivity.initialize(this);
        
        // Start monitoring
        cyberActivity.startMonitoring();
    }
}
```

### Advanced Configuration

```java
// Configure SiaraShield with custom settings
CyberActivity cyberActivity = new CyberActivity();
cyberActivity.setMonitoringLevel(CyberActivity.MONITORING_LEVEL_HIGH);
cyberActivity.setThreatDetectionEnabled(true);
cyberActivity.initialize(this);
```

## Permissions

The library requires the following permissions (automatically added):

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

## Requirements

- Android API level 21+ (Android 5.0)
- Java 8+
- Internet connectivity for threat detection features

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For support and questions, please contact:
- Email: support@cybersiara.com
- GitHub Issues: [Create an issue](https://github.com/CyberSiara/siarashield-android/issues)

## Changelog

### Version 1.4
- Initial release
- Basic cyber activity monitoring
- Threat detection capabilities
- Security analytics features