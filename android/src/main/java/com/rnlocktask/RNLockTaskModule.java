
package com.rnlocktask;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNLockTaskModule extends ReactContextBaseJavaModule {


  private static final String ACTIVITY_GONE = "ACTIVITY_GONE";
  private static final String DEVICE_OWNER_CLEARED = "DEVICE_OWNER_CLEARED";
  private static final String LOCKED_TASK = "LOCKED_TASK";
  private static final String LOCKED_TASK_AS_OWNER = "LOCKED_TASK_AS_OWNER";
  private static final String UNLOCKED_TASK = "UNLOCKED_TASK";

  public RNLockTaskModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNLockTask";
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
  public void startLockTask(Promise promise) {
    try {
      Activity mActivity = getCurrentActivity();
      if (mActivity != null) {
        DevicePolicyManager myDevicePolicyManager = (DevicePolicyManager) mActivity.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName mDPM = new ComponentName(mActivity, MyAdmin.class);

        if (myDevicePolicyManager.isDeviceOwnerApp(mActivity.getPackageName())) {
          String[] packages = {mActivity.getPackageName()};
          myDevicePolicyManager.setLockTaskPackages(mDPM, packages);
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
