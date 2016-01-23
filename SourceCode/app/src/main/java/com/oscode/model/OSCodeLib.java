package com.oscode.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by whailong on 14/1/16.
 */
@AVClassName("OSCodeLib")
public class OSCodeLib extends AVObject {

//    private String libName;
//    private String author;
//    private String type;
//    private String[] tags;
//    private String descriptionEN;
//    private String descriptionCN;
//    private String githubAddress;
//    private String license;
//    private String size;
//    private String minSDKVersion;
//    private int collectionCount;
//    private int downloadCount;
//    private int viewCount;
//    private AVFile libApkFile;
//    private AVFile libPreImage;


    public OSCodeLib() {
        super();
        this.put("viewCount", 0);
        this.put("collectionCount", 0);
        this.put("downloadCount", 0);
    }

    public AVFile getLibApkFile() {
        return getAVFile("libApkFile");
    }

    public void setLibApkFile(AVFile libApkFile) {
        this.put("libApkFile", libApkFile);
    }

    public AVFile getLibPreImage() {
        return getAVFile("libPreImage");
    }

    public void setLibPreImage(AVFile libPreImage) {
        this.put("libPreImage", libPreImage);
    }

    public String getLibName() {
        return this.getString("libName");
    }

    public void setLibName(String libName) {
        this.put("libName", libName);
    }

    public String getAuthor() {
        return this.getString("author");
    }

    public void setAuthor(String author) {
        this.put("author", author);
    }

    public String getType() {
        return this.getString("type");
    }

    public void setType(String type) {
        this.put("type", type);
    }

    public ArrayList<String> getTags() {
        ArrayList<String> list = new ArrayList<String>();
        JSONArray jsonArray = this.getJSONArray("tags");
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                try {
                    list.add(jsonArray.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public void setTags(ArrayList<String> tags) {
        JSONArray jsonArray = new JSONArray(tags);
        this.put("tags", jsonArray);
    }

    public String getDescriptionEN() {
        return this.getString("descriptionEN");
    }

    public void setDescriptionEN(String descriptionEN) {
        this.put("descriptionEN", descriptionEN);
    }

    public String getDescriptionCN() {
        return this.getString("descriptionCN");
    }

    public void setDescriptionCN(String descriptionCN) {
        this.put("descriptionCN", descriptionCN);
    }

    public String getGithubAddress() {
        return this.getString("githubAddress");
    }

    public void setGithubAddress(String githubAddress) {
        this.put("githubAddress", githubAddress);
    }

    public String getLicense() {
        return this.getString("license");
    }

    public void setLicense(String license) {
        this.put("license", license);
    }

    public String getSize() {
        return this.getString("size");
    }

    public void setSize(String size) {
        this.put("size", size);
    }

    public String getMinSDKVersion() {
        return this.getString("minSDKVersion");
    }

    public void setMinSDKVersion(String minSDKVersion) {
        this.put("minSDKVersion", minSDKVersion);
    }

    public int getCollectionCount() {
        return this.getInt("collectionCount");
    }

    public int increaseCollectionCount() {
        this.increment("collectionCount");
        return this.getCollectionCount();
    }

    public int decreaseCollectionCount() {
        this.increment("collectionCount", -1);
        return this.getCollectionCount();
    }

    public void setCollectionCount(int collectionCount) {
        this.put("collectionCount", collectionCount);
    }

    public int getDownloadCount() {
        return this.getInt("downloadCount");
    }

    public int increaseDownloadCount() {
        this.increment("downloadCount");
        return this.getDownloadCount();
    }

    public int decreaseDownloadCount() {
        this.increment("downloadCount", -1);
        return this.getDownloadCount();
    }

    public void setDownloadCount(int downloadCount) {
        this.put("downloadCount", downloadCount);
    }

    public int getViewCount() {
        return this.getInt("viewCount");
    }

    public int increaseViewCount() {
        this.increment("viewCount");
        return this.getViewCount();
    }

    public int decreaseViewCount() {
        this.increment("viewCount", -1);
        return this.getViewCount();
    }

    public void setViewCount(int viewCount) {
        this.put("viewCount", viewCount);
    }
}
