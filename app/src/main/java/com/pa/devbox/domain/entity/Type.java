package com.pa.devbox.domain.entity;

import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 20/1/16 00:03.
 * Email: whailong2010@gmail.com
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class Type implements Serializable {

    private String objectId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    private String enDescription;

    private String parentType;

    public String getEnDescription() {
        return enDescription;
    }

    public void setEnDescription(String enDescription) {
        this.enDescription = enDescription;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }
}
