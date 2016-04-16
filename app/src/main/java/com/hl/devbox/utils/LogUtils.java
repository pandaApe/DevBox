package com.hl.devbox.utils;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 23/2/16 22:40.
 * @Email: whailong2010@gmail.com
 */
public class LogUtils {

    public static boolean ISDEBUG = false;

    public static <T> void log(T c, String msg) {
        if (ISDEBUG)
            if (c instanceof Activity)
                Log.d(((Activity) c).getClass().getSimpleName(), msg);
            else if (c instanceof Fragment)
                Log.d(((Fragment) c).getClass().getSimpleName(), msg);

    }

    public static void log(String msg) {
        if (ISDEBUG)
            Log.d("AppLog", msg);

    }

}
