package com.pa.devbox.domain.entity.rest;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 12/5/16 22:08.
 * Email: whailong2010@gmail.com
 */
@JsonObject
public class CountIncrement {
    private Inner downloadCount;
    private Inner viewCount;

    public Inner getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Inner downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Inner getViewCount() {
        return viewCount;
    }

    public void setViewCount(Inner viewCount) {
        this.viewCount = viewCount;
    }
}
