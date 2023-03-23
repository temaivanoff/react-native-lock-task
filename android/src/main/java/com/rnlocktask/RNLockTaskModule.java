
package com.rnlocktask;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;

import java.util.ArrayList;

public class RNLockTaskModule extends ReactContextBaseJavaModule {


  private static final String ACTIVITY_GONE = "ACTIVITY_GONE";
  private static final String DEVICE_OWNER_CLEARED = "DEVICE_OWNER_CLEARED";
  private static final String LOCKED_TASK = "LOCKED_TASK";
  private static final String LOCKED_TASK_AS_OWNER = "LOCKED_TASK_AS_OWNER";
  private static final String UNLOCKED_TASK = "UNLOCKED_TASK";
  private static final String TAG = "RNLockTaskModule";

  public RNLockTaskModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNLockTask";
  }

  @ReactMethod
  public void isAppInLockTaskMode(Promise promise) {
    try {
      Activity mActivity = getCurrentActivity();
      if (mActivity != null) {
        ActivityManager activityManager = (ActivityManager) mActivity.getSystemService(Context.ACTIVITY_SERVICE);

        // When SDK version is 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          int lockTaskMode = activityManager.getLockTaskModeState();
          promise.resolve(lockTaskMode == ActivityManager.LOCK_TASK_MODE_PINNED);
        }

        //When SDK version <=21 and <23. This API is deprecated in 23.
        else {
          promise.resolve(activityManager.isInLockTaskMode());
        }
      }
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public  void clearDeviceOwnerApp(Promise promise) {
    try {
      Activity mActivity = getCurrentActivity();
      if (mActivity != null) {
        DevicePolicyManager myDevicePolicyManager = (DevicePolicyManager) mActivity.getSystemService(Context.DEVICE_POLICY_SERVICE);
        myDevicePolicyManager.clearDeviceOwnerApp(mActivity.getPackageName());
        promise.resolve(DEVICE_OWNER_CLEARED);
      }
      promise.reject(ACTIVITY_GONE, "Activity gone or mismatch");
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public void startLockTaskWith(ReadableArray additionalPackages, Promise promise) {
    try {
      Activity mActivity = getCurrentActivity();
      if (mActivity != null) {
        DevicePolicyManager myDevicePolicyManager = (DevicePolicyManager) mActivity.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName mDPM = new ComponentName(mActivity, MyAdmin.class);

        if (myDevicePolicyManager.isDeviceOwnerApp(mActivity.getPackageName())) {
          ArrayList<String> packages = new ArrayList<>();
          packages.add(mActivity.getPackageName());
          if(additionalPackages != null){
            for (int i = 0; i < additionalPackages.size(); i++) {
              packages.add(additionalPackages.getString(i));
            }
          }
          myDevicePolicyManager.setLockTaskPackages(mDPM, packages.toArray(new String[0]));
          mActivity.startLockTask();
          promise.resolve(LOCKED_TASK_AS_OWNER);
        } else {
          mActivity.startLockTask();
          promise.resolve(LOCKED_TASK);
        }
      } else{
        promise.reject(ACTIVITY_GONE, "Activity gone or mismatch");
      }
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public void startLockTask(Promise promise) {
    startLockTaskWith(null, promise);
  }

  @ReactMethod
  public  void stopLockTask(Promise promise) {
    try {
      Activity mActivity = getCurrentActivity();
      if (mActivity != null) {
        mActivity.stopLockTask();
        promise.resolve(UNLOCKED_TASK);
      } else {
          promise.reject(ACTIVITY_GONE, "Activity gone or mismatch");
      }
    } catch (Exception e) {
      promise.reject(e);
    }
  }
}
