package com.pa.devbox.domain.service;


import com.cm.retrofit2.converter.file.FileConverter;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 8/5/16 16:32.
 * Email: whailong2010@gmail.com
 */
public interface FileDownloadService {
    @GET
    Call<File> download(@Url String fileUrl, @Header(FileConverter.SAVE_PATH) String path);
}
