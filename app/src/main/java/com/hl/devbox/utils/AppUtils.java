package com.hl.devbox.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 1/4/16 22:24.
 * @Email: whailong2010@gmail.com
 */
public class AppUtils {

    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public synchronized static String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.apply();
            }
        }
        return uniqueID;
    }

}
