package com.hl.devbox.utils;

import org.kymjs.kjframe.utils.FileUtils;

import java.io.File;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 9/2/16 00:16.
 * @Email: whailong2010@gmail.com
 */
public class Config {

    public static final boolean ISDEBUGMODE = true;

    public static final String AppFolder = FileUtils.getSDCardPath() + File.separator + "DevBox" + File.separator;
    public static final String ReposInfoUrl = "https://api.github.com/repos/AUTHOR/NAME/branches";
    public static final String LastCommitInfoUrl = "https://api.github.com/repos/AUTHOR/NAME/git/commits/SHAVALUE";


}
