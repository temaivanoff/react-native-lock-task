
# react-native-lock-task

## Getting started

`$ npm install react-native-lock-task --save`

### Mostly automatic installation

`$ react-native link react-native-lock-task`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNLockTaskPackage;` to the imports at the top of the file
  - Add `new RNLockTaskPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-lock-task'
  	project(':react-native-lock-task').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-lock-task/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-lock-task')
  	```
**iOS, Windows not supported**

## Usage
```javascript
import RNLockTask from 'react-native-lock-task';

RNLockTask.startLockTask();
RNLockTask.stopLockTask();
```
