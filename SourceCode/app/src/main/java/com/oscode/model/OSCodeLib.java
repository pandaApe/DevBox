package com.oscode.model;

import android.annotation.SuppressLint;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.Date;

/**
 * Created by whailong on 14/1/16.
 */
@SuppressLint("ParcelCreator")
@AVClassName("OSCodeLib")
public class OSCodeLib extends AVObject {

    private String libName;
    private String author;
    private String thumbAddress;
    private String type;
    private String[] tags;
    private String descriptionEN;
    private String descriptionCN;
    private String githubAddress;
    private String license;
    private String size;
    private Date lastUpdate;
    private String minSDKVersion;
    private int collectionCounter;
    private int downloadCounter;
    private int viewCounter;

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbAddress() {
        return thumbAddress;
    }

    public void setThumbAddress(String thumbAddress) {
        this.thumbAddress = thumbAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getDescriptionCN() {
        return descriptionCN;
    }

    public void setDescriptionCN(String descriptionCN) {
        this.descriptionCN = descriptionCN;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getMinSDKVersion() {
        return minSDKVersion;
    }

    public void setMinSDKVersion(String minSDKVersion) {
        this.minSDKVersion = minSDKVersion;
    }

    public int getCollectionCounter() {
        return collectionCounter;
    }

    public void setCollectionCounter(int collectionCounter) {
        this.collectionCounter = collectionCounter;
    }

    public int getDownloadCounter() {
        return downloadCounter;
    }

    public void setDownloadCounter(int downloadCounter) {
        this.downloadCounter = downloadCounter;
    }

    public int getViewCounter() {
        return viewCounter;
    }

    public void setViewCounter(int viewCounter) {
        this.viewCounter = viewCounter;
    }
}
