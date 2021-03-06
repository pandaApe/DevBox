package com.pa.devbox.domain.entity;

import android.content.Context;

import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.pa.devbox.R;

import java.io.Serializable;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 14/1/16 21:32.
 * Email: whailong2010@gmail.com
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class Library implements Serializable {

    private String objectId;
    private String name;
    private String author;
    private String enDescription;
    private String cnDescription;
    private String githubAddress;
    private String license;

    private String minSdkVersion;
    private int collectionCount;
    private int downloadCount;
    private int viewCount;

    private DevFile apk;

    private DevFile image;

    public Library() {
        this.collectionCount = 0;
        this.downloadCount = 0;
        this.viewCount = 0;
    }

    @JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
    public static class DevFile implements Serializable {

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

        @JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
        public static class MetaData implements Serializable {


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
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEnDescription() {
        return enDescription;
    }

    public void setEnDescription(String enDescription) {
        this.enDescription = enDescription;
    }

    public String getCnDescription() {
        return cnDescription;
    }

    public void setCnDescription(String cnDescription) {
        this.cnDescription = cnDescription;
    }

    public String getGithubAddress() {
        return githubAddress;
    }

    public void setGithubAddress(String githubAddress) {
        this.githubAddress = githubAddress;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(String minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public DevFile getApk() {
        return apk;
    }

    public void setApk(DevFile apk) {
        this.apk = apk;
    }

    public DevFile getImage() {
        return image;
    }

    public void setImage(DevFile image) {
        this.image = image;
    }
}
