
# react-native-lock-task

## Getting started

`$ npm install react-native-lock-task --save`

### Mostly automatic installation

`$ react-native link react-native-lock-task`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-lock-task` and add `RNLockTask.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNLockTask.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

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

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNLockTask.sln` in `node_modules/react-native-lock-task/windows/RNLockTask.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Lock.Task.RNLockTask;` to the usings at the top of the file
  - Add `new RNLockTaskPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNLockTask from 'react-native-lock-task';

// TODO: What to do with the module?
RNLockTask;
```
  