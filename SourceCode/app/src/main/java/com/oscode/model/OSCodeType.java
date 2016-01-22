package com.oscode.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by whailong on 20/1/16.
 */
@AVClassName("OSCodeType")
public class OSCodeType extends AVObject {

    //    private int numCount;
//    private String nameCN;
//    private String nameEN;
    private String type;

    public OSCodeType() {
        super();
        this.put("numCount", 0);
    }

    public String getType() {
        return getString("type");
    }

    public void setType(String type) {
        this.put("type", type);
    }

    public int getNumCount() {
        return getInt("numCount");
    }

    public int increase() {
        increment("numCount");
        return this.getNumCount();
    }

    public String getNameCN() {
        return getString("nameCN");
    }

    public void setNameCN(String nameCN) {
        this.put("nameCN", nameCN);
    }

    public String getNameEn() {
        return getString("nameEN");
    }

    public void setNameEn(String nameEn) {
        this.put("nameEN", nameEn);
    }

    public String getFullTitle() {
        return this.getNameCN() + "(" + this.getNameEn() + ")";
    }
}
