package com.hl.devbox.domain.entity;

import com.google.gson.annotations.Until;

import java.io.Serializable;

/**
 * Created by whailong on 14/1/16.
 */
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

    @Until(0.1)
    private boolean isCollected;

    public boolean isCollected() {
        return isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
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
