package com.zsl.test.basezbproject.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;

import java.io.File;

/**
 * @author Created by stone
 * @since 2018/7/13
 * app常用的工具类
 */

public class PhoneUtils {


    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    //获取应用程序名称
    public static String getAppName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            //这种方式是可以的
            //String name = applicationInfo.loadLabel(pm).toString();
            int labelRes = applicationInfo.labelRes;
            String name = context.getResources().getString(labelRes);
            return name;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    //获取应用程序版本名称
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    //判断sd卡是否可用
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    //获取sd卡路径
    public static String getSDCardPath() {
        File file = Environment.getExternalStorageDirectory();
        String path = file.getAbsolutePath() + File.separator;
        return path;
    }

    //获取系统存储路径
    public static String getRootDirectoryPath() {
        File rootDirectory = Environment.getRootDirectory();
        String path = rootDirectory.getAbsolutePath();
        return path;
    }

    //获取sd卡的剩余存储空间  单位byte
    public static long getSDCardAllSize() {
        StatFs statFs = new StatFs(getSDCardPath());
        //获取空闲的数据块的数量
        long availableBlocksLong = statFs.getAvailableBlocks();
        //获取单个数据块的大小
        long blockSize = statFs.getBlockSize();
        return availableBlocksLong * blockSize;
    }

    public static int sp2px(Context context, int i) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float fontScale = displayMetrics.scaledDensity;
        return (int) (i * fontScale + 0.5f);
    }


    //dp转化成px   其他的模仿就行了  因为1dp=1sp的
    public static int dp2px(Context context, float dpValue) {
        Resources resources = context.getResources();
        //用于存储显示的通用信息，如显示大小，分辨率和字体。
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        //获取转化新系数（这里不太懂 要好好搞一下）
        float scale = displayMetrics.density;
        //在160dpi里面
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        Resources resources = context.getResources();
        //用于存储显示的通用信息，如显示大小，分辨率和字体。
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        //获取转化新系数（这里不太懂 要好好搞一下）
        float scale = displayMetrics.density;
        //在160dpi里面
        return (int) (pxValue / scale + 0.5f);
    }

}
