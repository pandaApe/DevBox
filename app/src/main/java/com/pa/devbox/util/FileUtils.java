package com.pa.devbox.util;

import android.os.Environment;

import java.io.File;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 9/5/16 23:17.
 * Email: whailong2010@gmail.com
 */
public class FileUtils {

    public static String getSdCardPath() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator;
    }
}
