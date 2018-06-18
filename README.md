
# react-native-lock-task

## Getting started

`$ npm install react-native-lock-task --save`

### Mostly automatic installation

`$ react-native link react-native-lock-task`

### Settings

**`yourProject/android/app/src/main/AndroidManifest.xml`**

```diff
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example">
    <uses-permission android:name="android.permission.MANAGE_DEVICE_ADMINS" />
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme"
        android:launchMode="singleTask"
        android:stateNotNeeded="true">

        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
+               <category android:name="android.intent.category.HOME" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

+       <receiver android:name="com.example.root.test3.MyAdmin"
+           android:label="@string/sample_device_admin"
+           android:description="@string/sample_device_admin_description"
+           android:permission="android.permission.BIND_DEVICE_ADMIN">
+           <meta-data android:name="android.app.device_admin"
+               android:resource="@xml/my_admin" />
+           <intent-filter>
+               <action android:name="android.app.action.DEVICE_ADMIN_ENABLED" />
+           </intent-filter>
+       </receiver>
    </application>
</manifest>
```

## Usage
```javascript
import RNLockTask from 'react-native-lock-task';

RNLockTask.startLockTask();
RNLockTask.stopLockTask();
```
