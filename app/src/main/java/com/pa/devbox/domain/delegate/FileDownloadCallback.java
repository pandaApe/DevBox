package com.pa.devbox.domain.delegate;

import java.io.File;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 9/5/16 23:23.
 * Email: whailong2010@gmail.com
 */
public interface FileDownloadCallback {

    void onSuccess(File file);

    void onFailure(Throwable t);

    void onProgress(long bytesRead, long contentLength, boolean done);
}
