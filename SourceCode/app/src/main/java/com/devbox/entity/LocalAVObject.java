package com.devbox.Entity;

/**
 * Created by whailong on 31/1/16.
 */
public class LocalAVObject {

    private String id;
    private String objectStr;

    public LocalAVObject() {
        super();
    }

    public LocalAVObject(String id, String objectStr) {
        super();
        this.id = id;
        this.objectStr = objectStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectStr() {
        return objectStr;
    }

    public void setObjectStr(String objectStr) {
        this.objectStr = objectStr;
    }
}
