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
    public static final String APPKey = "OkaU2qxpo1fbHPtc9o7yQVgM";
    public static final String APPId = "OOhkF87ffBYBHmvph465ApmV-gzGzoHsz";
    public static final String MasterKey = "b1PRoxgF967vn4cXalY3DK0Y";

    //Api URL
    private static final String BaseURL = "https://api.leancloud.cn";
    private static final String ApiVersion = "1.1";

    public static final String GetLibrariesURL = BaseURL + "/" + ApiVersion + "/classes/Library";
    public static final String GetTypesURL = BaseURL + "/" + ApiVersion + "/classes/Type";
    public static final String GetLoginURL = BaseURL + "/" + ApiVersion + "/classes/Library";
    public static final String UpdateLibraryURL = BaseURL + "/" + ApiVersion + "/classes/Library/";


}
