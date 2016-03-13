package com.hl.devbox.Entity;

import java.io.Serializable;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 13/3/16 14:40.
 * @Email: whailong2010@gmail.com
 */
public class DevFile implements Serializable {

    class MetaData implements Serializable{
        private String owner;
        private long size;

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }
    }

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

    public String getApkSizeStr() {

        double size = this.getMetaData().size / 1000.0 / 1000.0;
        double sizeFinal = Math.round(size * 100) / 100.0;
        return "下载(" + sizeFinal + "MB)";
    }

}
