# SiaraShield Integration Example

This example demonstrates how to integrate the SiaraShield Android library into your project.

## Project Setup

### 1. Add JitPack Repository

In your project's `build.gradle` (Project level):

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### 2. Add SiaraShield Dependency

In your app's `build.gradle` (Module level):

```gradle
dependencies {
    implementation 'com.github.CyberSiara:siarashield-android:1.4'
    
    // Other dependencies
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.8.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
}
```

### 3. Add Required Permissions

In your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

## Implementation Examples

### Basic Implementation

```java
package com.example.myapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.cybersiara.app.CyberActivity;

public class MainActivity extends AppCompatActivity {
    
    private CyberActivity cyberActivity;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize SiaraShield
        initializeSiaraShield();
    }
    
    private void initializeSiaraShield() {
        cyberActivity = new CyberActivity();
        
        // Initialize with default settings
        cyberActivity.initialize(this);
        
        // Start monitoring
        cyberActivity.startMonitoring();
        
        // Optional: Set up listeners for security events
        setupSecurityListeners();
    }
    
    private void setupSecurityListeners() {
        // Example of setting up custom security event handling
        // This would depend on the actual CyberActivity API
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        // Stop monitoring when activity is destroyed
        if (cyberActivity != null) {
            cyberActivity.stopMonitoring();
        }
    }
}
```

### Advanced Implementation with Custom Configuration

```java
package com.example.myapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.cybersiara.app.CyberActivity;

public class AdvancedMainActivity extends AppCompatActivity {
    
    private CyberActivity cyberActivity;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize SiaraShield with custom configuration
        initializeAdvancedSiaraShield();
    }
    
    private void initializeAdvancedSiaraShield() {
        cyberActivity = new CyberActivity();
        
        // Configure monitoring level (if available in API)
        // cyberActivity.setMonitoringLevel(CyberActivity.MONITORING_LEVEL_HIGH);
        
        // Enable threat detection (if available in API)
        // cyberActivity.setThreatDetectionEnabled(true);
        
        // Set custom security policies (if available in API)
        // cyberActivity.setSecurityPolicy(customPolicy);
        
        // Initialize with custom settings
        cyberActivity.initialize(this);
        
        // Start monitoring
        cyberActivity.startMonitoring();
        
        // Set up comprehensive security monitoring
        setupAdvancedSecurityMonitoring();
    }
    
    private void setupAdvancedSecurityMonitoring() {
        // Example of advanced security monitoring setup
        // This would include:
        // - Network monitoring
        // - Device state monitoring
        // - Threat detection callbacks
        // - Security analytics
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        // Resume monitoring when activity resumes
        if (cyberActivity != null) {
            cyberActivity.resumeMonitoring();
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        
        // Pause monitoring when activity pauses
        if (cyberActivity != null) {
            cyberActivity.pauseMonitoring();
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        // Stop monitoring when activity is destroyed
        if (cyberActivity != null) {
            cyberActivity.stopMonitoring();
        }
    }
}
```

### Service Implementation

```java
package com.example.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.cybersiara.app.CyberActivity;

public class SiaraShieldService extends Service {
    
    private CyberActivity cyberActivity;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        // Initialize SiaraShield in service
        cyberActivity = new CyberActivity();
        cyberActivity.initialize(this);
        cyberActivity.startMonitoring();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Service logic here
        return START_STICKY;
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
        if (cyberActivity != null) {
            cyberActivity.stopMonitoring();
        }
    }
}
```

## Testing Integration

### Unit Test Example

```java
package com.example.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.cybersiara.app.CyberActivity;

@RunWith(MockitoJUnitRunner.class)
public class SiaraShieldIntegrationTest {
    
    @Mock
    private Context mockContext;
    
    @Test
    public void testSiaraShieldInitialization() {
        // Test SiaraShield initialization
        CyberActivity cyberActivity = new CyberActivity();
        
        // Verify initialization doesn't throw exceptions
        cyberActivity.initialize(mockContext);
        
        // Test monitoring start/stop
        cyberActivity.startMonitoring();
        cyberActivity.stopMonitoring();
    }
}
```

## Troubleshooting

### Common Issues

1. **Build Error**: Make sure JitPack repository is added
2. **Permission Denied**: Ensure all required permissions are declared
3. **Initialization Error**: Check if context is valid and not null

### Debug Tips

1. Enable logging to see SiaraShield activity
2. Check network connectivity for threat detection features
3. Verify Android API level compatibility (21+)

## Next Steps

1. Test the integration in your development environment
2. Customize security policies based on your app's requirements
3. Implement proper error handling and user feedback
4. Consider implementing security analytics dashboard
