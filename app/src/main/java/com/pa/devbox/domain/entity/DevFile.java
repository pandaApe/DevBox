package com.pa.devbox.domain.entity;

import android.content.Context;

import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.pa.devbox.R;

import java.io.Serializable;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 13/3/16 14:40.
 * Email: whailong2010@gmail.com
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class DevFile implements Serializable {

    private String url;
    private MetaData metaData;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getApkSizeStr(Context context) {

        double size = this.getMetaData().getSize() / 1000.0 / 1000.0;
        double sizeFinal = Math.round(size * 100) / 100.0;
        return context.getString(R.string.download) + sizeFinal + "MB)";
    }

}
