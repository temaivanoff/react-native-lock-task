
# react-native-lock-task

## Getting started

`$ npm install react-native-lock-task --save`

### Mostly automatic installation

`$ react-native link react-native-lock-task`

### Settings

**`yourProject/android/app/src/main/AndroidManifest.xml`**

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.systemsettingexample"
    android:versionCode="1"
    android:versionName="1.0">

    <!-- switchWifiSilence() -->
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>

    <!-- switchBluetoothSilence() -->
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
    
    ...

</manifest>
```

## Usage
```javascript
import RNLockTask from 'react-native-lock-task';

RNLockTask.startLockTask();
RNLockTask.stopLockTask();
```
