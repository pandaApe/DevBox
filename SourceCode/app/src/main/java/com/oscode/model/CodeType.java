package com.oscode.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by whailong on 20/1/16.
 */
@AVClassName("CodeType")
public class CodeType extends AVObject {

//    private int numCount;
//    private String nameCN;
//    private String nameEN;

    public CodeType() {
        super();
        this.put("numCount", 0);
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
}
